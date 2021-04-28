### ArrayList源码
```
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```
- 继承AbstractList，AbstractList继承AbstractCollection
- AbstractCollection提供了Collection接口的骨干实现，AbstractList提供了List接口的骨干实现，减少了实现接口所需工作
- 实现RandomAccess,支持快速随机访问
```
    // Collections的binarySearch方法
    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        // 此处判断是否实现了RandomAccess接口
        if (list instanceof RandomAccess || list.size()<BINARYSEARCH_THRESHOLD)
            return Collections.indexedBinarySearch(list, key);
        else
            return Collections.iteratorBinarySearch(list, key);
    }
    // 使用while循环进行二分查找
    private static <T> int indexedBinarySearch(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size()-1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = list.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found
    }
    // 使用迭代器进行二分查找
    private static <T> int iteratorBinarySearch(List<? extends Comparable<? super T>> list, T key)
    {
        int low = 0;
        int high = list.size()-1;
        ListIterator<? extends Comparable<? super T>> i = list.listIterator();

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = get(i, mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found
    }
    // ArrayList用for循环遍历比iterator迭代器遍历快，LinkedList用iterator迭代器遍历比for循环遍历快
```
- trimToSize()瘦身方法
```
    /**
     * 因为容量常常会大于实际元素的数量。内存紧张时，可以调用该方法删除预留的位置，调整容量为元素实际数量。
     * 如果确定不会再有元素添加进来时也可以调用该方法来节约空间
     */
    public void trimToSize() {
        modCount++;
        // 如果size小于length
        if (size < elementData.length) {
            // 重新将elementData设置大小为size
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }
```
- ensureCapacity(int minCapacity)设置所需的容量
```
    /**
     * 使用指定参数设置数组容量
     *
     * @param minCapacity 所需的最小容量
     */
    public void ensureCapacity(int minCapacity) {
        //如果数组为空，容量预取0，否则去默认值(10)
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) ? 0 : DEFAULT_CAPACITY;
        //若参数大于预设的容量，在使用该参数进一步设置数组容量
        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }
    
    /**
     * 判断是否需要扩容
     *
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // 如果最小需要空间比elementData的内存空间要大，则需要扩容
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    
    /**
     * 扩容，以确保它可以至少持有由参数指定的元素的数目
     *
     * @param minCapacity 所需的最小容量
     */
    private void grow(int minCapacity) {
        // 获取到ArrayList中elementData数组的内存空间长度
        int oldCapacity = elementData.length;
        // 扩容至原来的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 再判断一下新数组的容量够不够，够了就直接使用这个长度创建新数组，
        // 不够就将数组长度设置为需要的长度
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        //若预设值大于默认的最大值检查是否溢出
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // 调用Arrays.copyOf方法将elementData数组指向新的内存空间时newCapacity的连续空间
        // 并将elementData的数据复制到新的内存空间
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
    /**
     * 检查是否溢出，若没有溢出，返回最大整数值(java中的int为4字节，所以最大为0x7fffffff)或默认最大值
     *
     * @param minCapacity
     * @return
     */
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0)   //溢出
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }
```
- add方法
```
    /**
     * 添加一个值，首先会确保容量
     *
     * @param e 要添加到此列表中的元素
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        // 扩容
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        // 将e赋值给elementData的size+1的位置
        elementData[size++] = e;
        return true;
    }
    
    /**
     * 得到最小扩容量
     *
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }
    
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }
    
    /**
     * 判断是否需要扩容
     *
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // 如果最小需要空间比elementData的内存空间要大，则需要扩容
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
```
