# ToastUtils 简介

- 可拓展
- 线程可用
- 防止多个Toast重复显示问题


## 入门指南

设置依赖项
```
implementation 'support.lfp:toast:1.0'
```

在任何地方调用showSimple方法来显示Toast
```
ToastUtils.showSimple("Toast info ...");
```

创建一个自定义的Toast
```
ToastUtils.build().create();
```


## 问题反馈

如果你在使用ToastUtils中遇到任何问题可以提[Issues](https://github.com/ftmtshuashua/ToastUtils/issues)出来。另外欢迎大家为ToastUtils贡献智慧，欢迎大家[Fork and Pull requests](https://github.com/ftmtshuashua/ToastUtils)。

如果觉得对你有用的话，点一下右上的星星赞一下吧。

## LICENSE

```
Copyright (c) 2018-present, ToastUtils Contributors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
