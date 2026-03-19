# 🚀 用户管理系统 (User Management System)

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen?style=flat-square&logo=springboot)
![Vue](https://img.shields.io/badge/Vue-3.x-4fc08d?style=flat-square&logo=vuedotjs)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql)
![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=openjdk)
![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)
![Build](https://img.shields.io/badge/Build-Passing-success?style=flat-square)

一个基于 **Spring Boot 3** 和 **Vue 3** 开发的前后端分离全栈项目。
# 🚀 用户管理系统 (User Management System)

一个基于 **Spring Boot 3** 和 **Vue 3** 开发的前后端分离全栈项目。

## 🌟 核心功能
- **智能搜索**：前端输入框自动识别输入内容，支持按 **用户ID** 或 **用户姓名** 实时检索。
- **全栈 CRUD**：实现了用户信息的增加、删除、查询及列表展示，打通了从数据库到页面的完整链路。
- **异步交互**：前端使用原生 Fetch API 与后端进行 RESTful 通信。
- **环境配置**：解决了跨域 (CORS) 问题，支持本地快速部署。

## 🛠️ 技术栈
### 后端 (Backend)
- **核心框架**：Spring Boot 3.4.3
- **持久层**：MyBatis (注解式开发)
- **数据库**：MySQL 8.0
- **依赖管理**：Maven

### 前端 (Frontend)
- **框架**：Vue 3 (Composition API)
- **构建工具**：Vite
- **状态管理**：Ref 响应式变量

## 📂 项目结构
```text
demo2/
├── my-frontend/          # Vue 前端项目根目录
│   └── src/App.vue       # 前端核心逻辑与界面
├── src/main/java/        # Spring Boot 后端源码
│   ├── controller/       # 接口层 (处理请求)
│   ├── service/          # 业务层 (逻辑处理)
│   └── mapper/           # 数据层 (SQL 映射)
└── pom.xml               # 后端依赖配置

🚀 快速启动
数据库准备：在MySQL中创建user表（包含id和name字段）。

启动：运行Demo2Application.java。

启动前端：cd my-frontend
npm run dev

访问地址：http://localhost:5173

---

### 💡 如何在 IDEA 里操作？

1.  **新建文件**：在 IDEA 左侧的项目树里，右键点击最顶层的 **`demo2`** 文件夹 -> **New** -> **File**。
2.  **命名**：输入 `README.md`（注意后缀是 `.md`，代表 Markdown 格式）。
3.  **粘贴**：把上面的模板复制进去保存。
4.  **同步到 GitHub**：
    ```bash
    git add README.md
    git commit -m "docs: 添加项目自述文件 README"
    git push origin main
    ```

---
