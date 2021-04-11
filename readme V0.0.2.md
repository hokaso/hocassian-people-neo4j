# 同和创作矩阵

## 一、综述

完整文档：https://bytedance.feishu.cn/docs/doccntc01xIMaW6diA2sNm7DhKb

（PS：markdown简直太不友好了……用户体验极差……所以有些细节就不放这边了）

### 1、项目简介

该矩阵旨在将现实/网络/虚构三位一体，创建多层世界维度之间的连接&发展关系，为创作者打造全身心投入的抽象虚拟现实生态环境；其中最顶层分为三大世界：里世界（story）、表世界（web）、现实世界（reality），这三个大脉络图谱又分别对应了不同的业务逻辑与需求。

### 2、项目规则

关于类无向图的约定：由于neo4j中无法记录无向关联，故在此针对部分双向关系进行约定；
- 关联信息写为双方关系（如父子、朋友、同学等），整个系统中不应存在双向关联；
- 节点&关系指向仅创建一次，且一律设置为指向他人，由先来后到的次序进行建立（如A认识B，B认识A也认识C，若按A、B、C的顺序填写，则，A创建A→B并填写关系；B创建B→C并填写关系，C不创建也不填写关系，但三者都有修改关联信息的权限）；如果为story中填写，则主作者按扩散关系逐个代入填写（由中心人物向外发散一轮，进行完毕后逐个代入角色继续增加二轮关系，以此类推）

## 二、里世界

里世界代表创作者内心虚拟出来的世界，比较出名的有妄想ADV、漫威宇宙、星球大战宇宙、红楼梦人物关系、斗罗大陆等等；该世界旨在帮助创作者更加具象地构造内心的虚拟世界，从创作本身提供协助。

### 1、最终设计

※段落节点属性受到 UniGal-Diagram Ver0.1.1 启发，以此为基础设计。

>
>UniGal标准可以用较为复杂但清晰的数据结构（统一建模语言）描述绝大多数的虚拟世界，本部分的设计参考了该标准中流程图的部分；

```
{
    "chapterStoryId": "011",
    "chapterStoryName": "示例章节",
    "chapterStoryDescription": "示例配置，用来告诉开发者各字段的详细构造。",
    // 没定义周目概念就默认为0
    "chapterStoryTimes": 0,
    // 只有确定了结局，即从此处往后再无分支时才有值，普通状态下此处为空
    "chapterStoryEnding": "",
    // 段落节点分为三类：1普通节点、2计算节点、3变动节点、4计算&变动节点，其中既没有计算又没有分歧/收束的节点可置为1，若包含计算使数值变动，置为2，若包含分歧/收束，置为3，若同时满足2和3的情况，置为4
    "chapterStoryType": "4",
    // 进入此段落需要满足的条件，其中在进入该节点前的上一个段落时，会在跳转的前一刻取出其下一段落节点对象中所有的该字段，按顺序判断后进入符合条件的分支（如包含计算，先完成计算后再判断）
    "chapterStoryCondiation": 
    [
        // 可多个条件
        {
            // key&value 均可自定义
            "key_1": "value_1",
            // op为条件运算符，可用的值为："=", "<", ">", ">=", "<="
            "op": "="
        },
        {
            // key&value 均可自定义
            "key_2": "value_2",
            // op为条件运算符，可用的值为："=", "<", ">", ">=", "<="
            "op": "<"
        }
    ],
    // 此段落节点中进行的运算
    "chapterStoryOperation": 
    [
        // 可多个变更
        {
            // 进入段落后各数值的运算方式，可用科学运算符为："+", "-", "*", "/", "square"（平方）, "not"（取反）, "set"（置为）
            "op": "+",
            // key为需要变更的字段名，value为该字段的值，其数据类型为int
            "key_1": 1
        },
        {
            // 进入段落后各数值的运算方式，可用科学运算符为："+", "-", "*", "/", "square"（平方）, "not"（取反）, "set"（置为）
            "op": "set",
            // key为需要变更的字段名，value为该字段的值，其数据类型为int
            "key_2": 0
        }
    ],
    // 可多个（表分歧）
    "chapterStoryNext": 
    [
        "012",
        "013"
    ],
    // 可多个（表收束）
    "chapterStoryPrevious": 
    [
        "010"
    ],
    // 附加信息，设为对象形式即可
    "addtional": {}
}
```

example（其实右边的两个节点可以合成一个，你们可以想想怎么设计）↓

![](https://hocassian.cn/wp-content/uploads/2021/02/e74b627c128d4706477222e09319ad8f.png)

作品与段落的关系为1对N，一部作品里有很多章节；作品属性和段落节点属性会以SQL的形式存储到关系型数据库MySQL，而人物节点属性和连接属性会以NoSQL的形式存储进非关系型数据库Neo4j中。

![](https://hocassian.cn/wp-content/uploads/2021/02/bf06202388bdd2ff9f76387bf2295cb8.png)

其中段落之间的关系通过桑基图表示↓

![](https://hocassian.cn/wp-content/uploads/2021/02/bd6589f0d7cc2827bdcbdda8be6b51d3.png)

段落节点、人物节点、连接属性这三大对象之间的关系为↓

![](https://hocassian.cn/wp-content/uploads/2021/02/b5a6f5a0017512ce3890462e0ce2d8e6.png)

※由于里世界过于复杂，目前仅设计&开发后端，待表世界&现实世界的前端开发完成后，再结合相关开发经验来完成里世界的前端设计&开发。

## 三、表世界

表世界代表的是当下人们的网络社交关系，比如社交平台的关注加好友、线上合作开发、虚拟资源共享等，都属于这一类；目前该世界侧重的属性有：活跃领域&沟通要旨，侧重这两点也是为了旨在帮助创作者更好地找到同盟，快速寻求资源，为某个创作主体的整体架构提供间接帮助。

## 四、现实世界

现实世界代表的是人们现实生活中的社交关系，俗话说「艺术来源于生活，却又高于生活」，取材是创作中必不可少的一环；该世界侧重于个体的性格与关系，为创作者厘清现实关系，为实际创作提供灵感。

## 五、唯一生物识别码

最后还有一层关系可以将这三个世界中的个体关联起来，这就是「唯一生物代码」；这类关系将存储于关系型数据库中，旨在进一步聚合个体之间的关系：

| 唯一生物编号 | 里世界   | 表世界   | 现实世界 | 备注 |
| ------------ | -------- | -------- | -------- | ---- |
| 001          | 张和id   | 同和君id | 何承翰id |      |
| 002          | 李梓豪id | 何白渝id | 吕国勇id |      |
| ……           | ……       | ……       | ……       |      |

## 六、开发相关

### **CRUD**相关代码

#### 1.创建节点

##### 1.1里世界

```
SQL
merge (p:PersonStory{
personStoryId:"",
personStoryName:"",
personStorySex:"",
personStoryInfo:"",
personStoryFeature:""  
}) return p  
```

##### 1.2表世界

```
SQL  
merge (p:PersonWeb{
personWebId:"",
personWebName:"",
personWebPlatform:"",
personWebInfo:"",
personWebField:"",
personWebKey:""
}) 
return p  
```

##### 1.3现实世界

```
SQL
merge (p:PersonReality{
personRealityId:"",
personRealityName:"",
personRealitySex:"",
personRealityBrithday:"",
personRealityInfo:"",
personRealityFeature:"",
personRealityKey:"",
personRealityAbility:""  
})
return p  
```

#### 2.创建关系

##### 2.1里世界

```
SQL
match(pa:PersonStory{
personStoryId:""  
}),(pb:PersonStory{
personStoryId:""  
}) merge (pa)-[c:ConnectStory{
"connectStoryId":  "",
"connectStoryName":"",
"connectStoryInfo":""
}]->(pb) return pa,pb,c  
```

##### 2.2表世界

```
SQL  match(pa:PersonWeb{
personWebId:""  
}),(pb:PersonWeb{
personWebId:""  
}) merge (pa)-[c:ConnectWeb{
"connectWebId":  "",
"connectWebName":"",
"connectWebInfo":""  
}]->(pb) return pa,pb,c  
```

##### 2.3现实世界

```
SQL
match(pa:PersonReality{
personRealityId:""
}),(pb:PersonReality{
personRealityId:""
}) merge (pa)-[c:ConnectReality{
"connectRealityId":  "",
"connectRealityName":"",
"connectRealityInfo":""
}]->(pb) return pa,pb,c
```

#### 3.呈现脉络

（**创建后将中心移动至新节点或新连接**）

##### 3.1里世界

```
SQL
MATCH (p:PersonStory) RETURN p  
```

##### 3.2表世界

```
SQL
MATCH (p:PersonWeb) RETURN p  
```

##### 3.3现实世界

```
SQL
MATCH (p:PersonReality) RETURN p  
```

#### 4.修改节点

##### 4.1里世界

第一步：返回节点详情

```
SQL
MATCH (p:PersonStory{
personStoryId:""
}) RETURN p  
```

第二步：更新节点详情

```
SQL
MATCH (p:PersonStory{
personStoryId: ""
})
SET p.personStoryName = "",
p.personStorySex = "",
p.personStoryInfo = "",
p.personStoryFeature = ""
RETURN p
```

##### 4.2表世界

第一步：返回节点详情

```
SQL
MATCH (p:PersonWeb{
personWebId:""
}) RETURN p
```

第二步：更新节点详情

```
SQL
MATCH (p:PersonWeb{
personWebId:""
})
SET p.personWebName = "",
p.personWebPlatform = "",
p.personWebField = "",
p.personWebInfo = "",
p.personWebKey = ""
RETURN p
```

##### 4.3现实世界

第一步：返回节点详情

```
SQL
MATCH (p:PersonReality{
personRealityId:""
}) RETURN p
```

第二步：更新节点详情

```
SQL
MATCH (p:PersonReality{
personRealityId:""
})
SET p.personRealityName = "",
p.personRealitySex =  "",
p.personRealityBrithday = "",
p.personRealityInfo =  "",
p.personRealityFeature = "",
p.personRealityKey = "",
p.personRealityAbility = ""
RETURN p
```

#### 5.修改关系

##### 5.1里世界

第一步：返回连接详情

```
SQL  MATCH ()-[c:ConnectStory{
connectStoryId:""
}]-()  RETURN c
```

第二步：更新连接详情

```
SQL  MATCH ()-[c:ConnectStory{
connectStoryId:""  
}]-()  
SET c.connectStoryName = "",
c.connectStoryInfo = ""
RETURN c
```

##### 5.2表世界

第一步：返回连接详情

```
SQL  MATCH ()-[c:ConnectWeb{
connectWebId:""
}]-()  RETURN c
```

第二步：更新连接详情

```
SQL  MATCH ()-[c:ConnectWeb{
connectWebId:""  }]-()
SET  c.connectWebName = "",
c.connectWebInfo = ""
RETURN c
```

##### 5.3现实世界

第一步：返回连接详情

```
SQL
MATCH ()-[c:ConnectReality{
connectRealityId:""
}]-()  RETURN c
```

第二步：更新连接详情

```
SQL
MATCH ()-[c:ConnectReality{
connectRealityId:""
}]-()
SET c.connectRealityName = "",
c.connectRealityInfo =  ""
RETURN c
```

#### 6.删除节点

##### 6.1里世界

```
SQL
MATCH (p:PersonStory{
personStoryId:""
})   DELETE p  
```

##### 6.2表世界

```
SQL
MATCH (p:PersonWeb{
personWebId:""
})   DELETE p  
```

##### 6.3现实世界

```
SQL
MATCH (p:PersonReality{
personRealityId:""
})   DELETE p
```

#### 7.删除关系

##### 7.1里世界

```
SQL
MATCH ()-[c:ConnectStory{
connectStoryId:""
}]-()  DELETE c
```

##### 7.2表世界

```
SQL
MATCH ()-[c:ConnectWeb{
connectWebId:""
}]-()  DELETE c
```

##### 7.3现实世界

```
SQL
MATCH ()-[c:ConnectReality{
connectRealityId:""
}]-()  DELETE c
```

### 后端代码

SpringBoot（框架）+Mybatis（持久层组件）+org.neo4j.driver.v1（数据库插件）

### 前端代码

vue.js（框架）+Echarts（渲染插件）

### 参考资料

https://blog.csdn.net/qq_34661106/article/details/103540826

https://blog.csdn.net/uniquewonderq/article/details/80924347

https://github.com/whl6785968/BackstageWaterForG

https://github.com/AntMxs/MyNeo4j

https://github.com/neo4j-contrib/neo4j-jdbc
