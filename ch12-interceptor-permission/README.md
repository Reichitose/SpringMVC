ch12-interceptor-permission

关于使用拦截器实现登录权限验证

实现步骤

创建一个login.jsp  模拟登录(把用户的信息放入session)

创建一个loginout.jsp 模拟退出(从session中删除用户信息)

创建拦截器从session中获取用户登录数据验证能否访问系统

创建一个验证的jsp,如果验证失败,给出提示

配置中声音声明组件扫描器和拦截器

