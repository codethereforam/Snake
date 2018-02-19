# Snake

Java.awt实现的符合面向对象思想的贪吃蛇游戏

## 组织结构

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── priv
│   │   │       └── thinkam
│   │   │           └── snake
│   │   │               ├── common -- 公用类
│   │   │               │   ├── AbstractSnake.java -- 蛇的一部分
│   │   │               │   ├── DirectionEnum.java -- 方向
│   │   │               │   ├── Drawable.java -- Drawable接口应由那些打算被画出来的类来实现
│   │   │               │   └── SnakeSectionColorEnum.java -- 蛇的一节的颜色
│   │   │               ├── controller -- 控制器
│   │   │               │   └── Controller.java -- 控制器
│   │   │               ├── model -- 模型
│   │   │               │   ├── Food.java -- 食物
│   │   │               │   ├── Snake.java -- 蛇
│   │   │               │   └── SnakeSection.java -- 蛇的一节
│   │   │               └── view -- 视图
│   │   │                   └── GameView.java -- 游戏视图
│   │   └── resources
│   └── test
│       └── java
└── pom.xml -- maven项目对象模型
```

## 项目运行

1. 下载代码并构建

```
git clone https://github.com/codethereforam/Snake.git
cd Snake && mvn clean package
```

2. 运行

- Windows

```
C:/> target/bin/Snake.bat
```

- *nix

```sh
$ sh target/bin/Snake
```

## 操作说明

方向键：“←”，“→”，“↑”，“↓”

## 游戏截图

![Snake](/Snake.png)

## 许可证

[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)
