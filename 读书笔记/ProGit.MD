- start:20220305
- https://git-scm.com/book/zh/v2(在线书)
### Git是什么
- 版本控制系统，VCS，Version Control System
### 版本控制
- 版本控制是一种记录一个或若干文件内容变化，以便将来查阅特定版本修订情况的系统
- 优点：
1. 可以保存文件的所有版本，文件可以是设计图纸，项目代码等
2. 历史版本回溯，回退，找到问题原因，谁 何时 导致的问题
3. 随意改动，一键回退
###### 本地版本控制
- 采用某种简单的数据库来记录文件的历次更新差异
- 例如：RCS,Mac OS X开发者工具包中可以使用rcs命令，在硬盘保存补丁集，计算各个版本的内容
- 瓶颈：不同系统的协同工作
###### 集中化的版本控制系统（CVCS:Centralized Version Control Systems）
- 一个单一的集中管理的服务器，保存所有版本，协同工作者连接服务器，取最新文件，提交文件
- 例如：CVS,Subversion，Perforce
- 优点：
1. 每个人都可以看到其他人做了什么
2. 管理员可以掌控每个人的权限
3. 比在各个客户端维护本地数据库更轻松
- 缺点：
1. 中央服务器的单点故障问题
###### 分布式版本控制系统(DVCS:Distributed Version Control System)
- 客户端不仅取最新版本的快照，而是把整个代码仓库完整的镜像下来
- 例如：Git，Mercurial，Bazaar，Darcs
优点：
1. 容灾性好，任一协同工作的服务器故障，都可用任何一个镜像出来的本地仓库恢复
### Git历史
- Linux开源项目使用BitKeeper进行版本控制，双方合作关系结束后，Linux团队开发了Git
### Git特点
- 直接记录快照，而非差异比较
- 近乎所有操作都是本地执行（上传除外，push）
- Git保证完成性，计算校验和：对文件内容做哈希计算，有改动，Git都知道
- 一般只添加数据：所有Git操作，几乎只往Git数据库中增加数据，很难让Git执行任何不可逆操作，只要提交快照到Git中，就难以再丢失数据
### Git三种状态
1. 已提交(committed)：数据已经安全的保存在本地数据库中
2. 已修改(modified)：修改了文件，但还没保存到数据库中
3. 已暂存(staged)：对一个已修改文件的当前版本做了标记，使之包含在下次提交的快照中
### Git项目的三个工作区域
1. Git仓库：Git用来保存项目的元数据和对象数据库的地方
2. 工作目录：对项目的某个版本独立提取出来的内容
3. 暂存区域：是一个文件，保存了下次将提交的文件列表信息，一般在Git仓库目录中，有时也被称作索引
### Git基本工作流程
1. 在工作目录修改文件
2. 暂存文件，将文件的快照放入暂存区域
3. 提交更新，找到暂存区域的文件，将快照永久性存储到Git仓库目录
### 使用Git命令行的好处
- Git命令行可以执行Git的所有操作，而GUI工具只是所有功能的子集以降低操作难度
- 所有机器都有命令行工具，不同人会安装不同的GUI工具
### Git的三种级别配置（优先级依次升高）
1. /etc/gitconfig:系统上每一个用户及他们仓库的通用配置，git config --system会读写此文件
2. ~/.gitconfig或~/.config/git/config:只针对当前用户，git config --global会读写此文件
```
~/.gitconfig或~/.config/git/config:只针对当前用户，git config --global会读写此文件
```
3. 当前使用仓库的Git目录中的config文件，就是.git/config，针对该仓库
### Git基础
###### 获取Git仓库
```
# 在现有目录中初始化仓库
git init
# 克隆现有仓库
git clone [url]
```
###### 记录每次更新到仓库
- 工作目录下的每一个文件就两种状态：已跟踪或未跟踪
- 已跟踪状态：未修改、已修改、已放入暂存区
```
# 检查当前文件状态，-s参数：紧凑的格式
git status
# 跟踪新文件
git add [files]
```
- status三种状态
1. untracked files:未跟踪文件
2. changes to be committed:已暂存状态，等待提交
3. changes not staged for commit:已跟踪文件发生了变化，未暂存
- 查看已暂存和未暂存的修改
```
# 查看为暂存的文件更新部分
git diff
# 查看已暂存文件和上次提交的更新部分
git diff --staged
# 查看可用diff插件
git difftool --tool-help
# 使用插件查看diff,vimdiff是工具之一
git difftool --tool=vimdiff
```
- 提交
```
# 需要弹出编辑器输入提交信息
git commit
# 弹出的编辑器会默认记录详细的修改内容
git commit -v
# 设定自己喜欢的交互编辑软件,一般为vim或emacs
git config --global core.editor
# 提交信息和命令放同一行
git commit -m "message"
# 跳过暂存部分
git commit -a
```
- 移除文件
```
# 移除工作目录文件并移出跟踪文件清单 -f强制
git rm test.txt
# 仅移出跟踪文件清单
git rm --cached test.txt
```
- 移动文件
```
# 类似于改名字
git mv test.txt demo.txt
# 相当于运行了下面三条命令
mv test.txt demo.txt
git rm test.txt
git add demo.txt
```
###### 查看提交历史
```
# 无参数查看,按时间先后顺序列出
git log
# -p或--patch参数，显示每次提交引入的差异 -数字限制日志条数
git log -p -2
# stat参数，简略统计信息
git log --stat
# pretty参数，在一行展示:oneline，其他还有short，full，fuller
git log --pretty=oneline
# format参数，定制记录的显示格式
git log --pretty=format:"%h - %an , %ar : %s"
%H 提交的完整哈希值
%h 提交的简写哈希值
%T 树的完整哈希值
%t 树的简写哈希值
%P 父提交的完整哈希值
%p 父提交的简写哈希值
%an 作者名字
%ae 作者的电子邮件地址
%ad 作者修订日期（可以用 --date=选项 来定制格式）
%ar 作者修订日期，按多久以前的方式显示
%cn 提交者的名字
%ce 提交者的电子邮件地址
%cd 提交日期
%cr 提交日期（距今多长时间）
%s 提交说明
# graph参数，使用一些ASCII字符串形象的展示你的分支、合并历史
git log --pretty=format:"%h %s" -- graph
# git log常用选项
-p              按补丁格式显示每个更新之间的差异。 
--stat          显示每次更新的文件修改统计信息。 
--shortstat     只显示--stat 中最后的行数修改添加移除统计。 
--name-only     仅在提交信息后显示已修改的文件清单。 
--name-status   显示新增、修改、删除的文件清单。 
--abbrev-commit 仅显示 SHA-1 的前几个字符，而非所有的 40 个字符。
--relative-date 使用较短的相对时间显示（比如，“2 weeks ago”）。 
--graph         显示 ASCII 图形表示的分支合并历史。 
--pretty        使用其他格式显示历史提交信息。可用的选项包括 oneline，short，full， fuller 和 format（后跟指定格式）。
# 限制输出长度
git log -2
# --since --until时间筛选,时间格式多种，“2018-01-14” 或 “2 years 1 day 3 minutes ago”
git log --since=2.weeks
# --author 指定作者的提交
# --grep指定提交说明中的关键字
# --all-match 多个选项同时匹配
# -S 添加或移除了某些字符串的提交
git log -S function_name
# 指定路径,路径放最后
git log -2 -- xxx/xx
# 限制git log输出的选项
-(n)                仅显示最近的 n 条提交 
--since , --after   仅显示指定时间之后的提交。 
--until , --before  仅显示指定时间之前的提交。 
--author            仅显示指定作者相关的提交。 
--committer         仅显示指定提交者相关的提交。 
--grep              仅显示含指定关键字的提交 
-S                  仅显示添加或移除了某个关键字的提交
# 实际例子，2008年10月期间 Junio Hamano提交的但未合并的测试文件
git log --pretty="%h - %s" --author=gitster --since="2008-10-01" --before="2008-11-01" --no-merges -- t/
```
###### 撤销操作
```
# 重新提交：漏掉文件未添加，或提交信息写错了。会将暂存区的文件提交，最终只会有一个提交
git commit --amend
# 取消暂存的文件,不加选项的reset安全，加上hard危险，会撤销工作目录中的文件
git reset HEAD xxx.txt
# 撤销对文件的修改,危险命令，在本地的任何修改都会消失
git checkout -- xxx.txt
```
###### 远程仓库的使用
```
# 查看远程仓库,加-v可显示需要读写远程仓库使用的Git保存的简写与其对应的URL
git remote
# 添加远程仓库
git remote add <shortname> <url>
# 从远程仓库中抓取与拉取（如何匹配分支？？？）
git fetch <remote>
# 如果当前分支设置了跟踪远程分支，可用git pull 拉取
# 推送到远程分支,将master推送到origin服务器
git push origin master
# 查看某个远程仓库,会列出URL及跟踪分支信息
git remote show <remote>
# 远程仓库的重命名与移除
git remote rename oldname newname
# 删除远程仓库
git remote remove <remote>
```
###### 打标签
- Git可以给仓库历史中的某一个提交打上标签，已示重要
- 轻量标签：像一个不会改变的分支——它只是某个特定提交的引用
- 附注标签：是存储在Git数据库中的一个完整对象，包括打标签者的名字，邮件地址，日期，标签信息
```
# 列出已有标签
git tag
# -l "keyword" 按照特定的模式查找,或--list
git tag -l "v1.0"
# 创建附注标签 -a选项表示创建附注标签，-m指定存储在标签中的信息，没有会启动编辑器要求你输入信息，同commit
git tag -a v1.4 -m "my version 1.4"
# 查看标签信息和与之对应的提交信息
git show v1.4
# 创建轻量标签，不需要使用-a，-s，-m选项
git tag v1.4.1
# 后期打标签，指定提交的校验和或部分校验和 git log --pretty=oneline 列出提交历史，首列为校验和
git tag -a v1.2 xxxx
# 共享标签，默认git push不会推送标签到远程仓库
git push origin v1.5
# 一次性推送很多标签,将所有不在远程仓库的标签全部推送，不区分轻量和附注
git push origin --tags
# 删除标签，本地
git tag -d <tagname>
# 删除本地后，更新远程
git push origin :refs/tags/<tagname>
# 更直观的方式
git push origin --delete <tagname>
# 检出标签，会使你的仓库处于“分离头指针”状态，做了更改提交，标签不会发生变化，但你的新提交将不属于任何分支，并且无法访问
git checkout <tagname>
# 常规操作，根据标签创建一个新的分支
git checkout -b <branchname> <tagname>
```
###### Git别名
```
# 如果不想每次都输入完整的Git命令，可以通过git config为每个命令设置别名
git config --global alias.co checkout
# 举例：解决取消暂存文件的易用性问题
git config --global alias.unstage 'reset HEAD --'
# 举例：查看最后一次提交
git config --global alias.last 'log -1 HEAD'
# 举例：与Git仓库协作工具的命令，在命令前加!符号
git config --global alias.visual '!gitk'
```
### Git分支
###### 分支简介
- 所有版本控制系统都以某种形式支持分支，从开发主线分离开，避免影响主线，其他版本控制系统中，很低效——需要创建一个源代码目录的副本，耗时
- Git提交操作
1. 保存一个树对象，保存了文件的校验和和修改，保存指向具体文件快照的指针
2. 创建一个提交对象，除了提交信息外以及指向父对象的指针外，还包含指向上述树对象的指针
3. 首次提交的提交对象没有父对象；普通提交会有一个父对象，即上一次的提交对象；由多个分支合并产生的提交对象有多个父对象
- Git的分支，本质上是指向提交对象的可变指针
- 创建分支时，仅仅创建了一个可以移动的新的指针
```
git branch testing
```
- HEAD特殊指针，指向当前所在的本地分支（可以看作当前分支的别名）
- 查看各个分支当前所指向的对象 --decorate
```
git log --oneline --decorate
```
- 分支切换,分支切换会改变你工作目录中的文件
```
git checkout testing
```
- 项目分叉历史,输出你的提交历史、各个分支的指向以及项目的分支分叉情况
```
git log --oneline --decorate --graph --all
```
- 创建新分支同时切换过去，以当前分支为基础创建；后面可加远程分支名，以远程分支为基础创建，并关联
```
git checkout -b <newbranchname>
```
- 查看本地分支及关联的分支
```
git branch -vv
```
###### 分支的新建与合并
- 实际场景
1. 开发某个网站
2. 为实现某个新的用户需求，创建一个分支
3. 在这个分支上开展工作。
- 突发情况,线上有个紧急问题需要修复
1. 切换到你的线上分支（production branch）
2. 为这个紧急任务新建一个分支，并在其中修复它
3. 在测试通过之后，切换回线上分支，然后合并这个修补分支，最后将改动推送到线上分支
4. 切换回你最初工作的分支上，继续工作。
- 具体操作
```
# 当前在master分支
# 新建一个新需求分支同时切换到那个分支上
git checkout -b newneed
# 正常开发及提交推送
# 线上突发问题
# 保证工作目录和暂存区里没有没提交的修改，切换回master
git checkout master
# 建立一个紧急修复分支并切换
git branch -b hotfix
# 做修复工作
# 将hotfix分支合并到master分支,hotfix提交对象是原master提交对象的直接后继节点，Git会直接将指针向前移动（顺着一个分支走下去能够到达另一个分支，没有需要解决的分歧：Fast-forward）
git checkout master
git merge hotfix
# 最新修改已在master，可以发布修复内容
# 回到之前打断的工作newneed，需要先删除hotfix，已经没用了
git branch -d hotfix
# 切回newneed
git checkout newneed
# 继续工作后，合并到master,此时的合并是一个三方合并，因为master节点不是newneed的直接祖先，三方是指：master，newneed，公共祖先提交节点
git checkout master
git merge newneed
# 如果有冲突，原因是两个分支中对统一部分进行了不同的修改，此时Git做了合并，不会自动的创建一个新的合并提交，可以使用git status查看产生冲突未合并的内容（unmerged）
# 打开冲突文件,======上方为当前分支即maste内容，下方为newneed分支内容，手动删除<<<<< >>>>>> ===== 并修改冲突内容
<<<<<<< HEAD:index.html
xxxxx
=======
xxxxxxaaa
>>>>>>> newneed:index.html
# 对修改后的文件使用git add 来将其标记为冲突已解决
# 使用图形化工具解决冲突,可配置使用的工具 git mergetool --tool-help
git mergetool
# 合并提交
git commit
```
###### 分支管理
