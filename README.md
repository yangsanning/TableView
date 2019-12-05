# TableView
[![](https://jitpack.io/v/yangsanning/TableView.svg)](https://jitpack.io/#yangsanning/TableView)
[![API](https://img.shields.io/badge/API-19%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=19)

## 效果预览

| [TableView]                      |
| ------------------------------- |
| <img src="images/image1.gif" height="512" /> |


## 主要文件
| 名字             | 摘要           |
| ---------------- | -------------- |
| [TableView] | 表格View  |


### 1. 基本用法

#### 1.1 布局中添加
```android
 <ysn.com.view.TableView
        android:id="@+id/main_activity_table_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:tv_head_layout_res_id="@layout/layout_table_head"
        app:tv_left_top_head_layout_res_id="@layout/layout_table_left_top_head" />
```

#### 1.2 adapter设置
```android
  tableView.setFirstColumnAdapter(firstColumnAdapter).setContentAdapter(contentAdapter);
```

### 2. 配置属性([Attributes])
|name|format|description|
|:---:|:---:|:---:|
| tv_left_top_head_layout_res_id | reference | 左上角布局 |
| tv_head_layout_res_id | reference | 头布局 |


### 3.添加方法

#### 3.1 添加仓库

在项目的 `build.gradle` 文件中配置仓库地址。

```android
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### 3.2 添加项目依赖

在需要添加依赖的 Module 下添加以下信息，使用方式和普通的远程仓库一样。

```android
implementation 'com.github.yangsanning:TableView:1.0.0'
```

[TableView]:https://github.com/yangsanning/TableView/blob/master/tableview/src/main/java/ysn/com/view/TableView.java
[Attributes]:https://github.com/yangsanning/TableView/blob/master/tableview/src/main/res/values/attrs.xml
