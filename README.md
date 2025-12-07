# FastAuth Demo - 微信公众号登录示例

基于 [FastAuth](https://github.com/yiancode/fastauth) 实现的微信公众号 OAuth2 登录示例项目。

## 功能特性

- 微信公众号网页授权登录
- 获取用户基本信息（昵称、头像、OpenID 等）
- 完整的登录/登出流程
- 简洁美观的 UI 界面

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/yiancode/fastauth-demo.git
cd fastauth-demo
```

### 2. 配置参数

创建 `src/main/resources/application-local.yml` 文件：

```yaml
fastauth:
  wechat-mp:
    client-id: 你的AppID
    client-secret: 你的AppSecret
    redirect-uri: http://你的域名/auth/callback
```

或者通过环境变量配置：

```bash
export WECHAT_APP_ID=你的AppID
export WECHAT_APP_SECRET=你的AppSecret
export WECHAT_REDIRECT_URI=http://你的域名/auth/callback
```

### 3. 配置内网穿透

本地开发需要配置内网穿透，推荐使用 [natapp](https://natapp.cn)：

```bash
./natapp -authtoken=你的authtoken
```

### 4. 配置微信公众号

1. 登录 [微信公众平台](https://mp.weixin.qq.com)
2. 进入 **设置与开发 → 公众号设置 → 功能设置**
3. 配置 **网页授权域名**（填写 natapp 分配的域名）

### 5. 运行项目

```bash
mvn spring-boot:run
```

访问 `http://你的域名/` 开始测试。

## 项目结构

```
src/main/java/com/yiancode/fastauthdemo/
├── FastAuthDemoApplication.java    # 启动类
├── config/
│   └── FastAuthConfig.java         # 配置类
└── controller/
    ├── AuthController.java         # 授权控制器
    └── PageController.java         # 页面控制器
```

## 技术栈

- Spring Boot 2.7
- FastAuth 1.0.0
- Thymeleaf
- Hutool HTTP

## 相关项目

- **FastAuth** - 轻量级第三方登录组件：[https://github.com/yiancode/fastauth](https://github.com/yiancode/fastauth)

## 后续计划

FastAuth 将持续扩展，计划支持更多平台和语言：

### 平台支持
- [x] 微信公众号
- [ ] 微信开放平台
- [ ] QQ 登录
- [ ] 微博登录
- [ ] GitHub 登录
- [ ] Google 登录

### 多语言实现
- [x] Java
- [ ] Go
- [ ] Python
- [ ] Node.js
- [ ] PHP

## 作者

**易安** - [GitHub](https://github.com/yiancode)

## 开源协议

MIT License
