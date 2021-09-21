ch04-处理器方法的返回值表示请求的处理结果

1.ModelAndView

同时有视图和数据,对视图有.forward

2.String

表示视图,可以是逻辑名称, 也可以是完整的视图路径

3.Void
    
不表示数据,也不表示视图
可以处理ajax,但没必要,需要大量的手动操作


4.Object
只有数据,没有视图,可以是返回多种对象,ajax使用合理

ajax主要使用json数据格式,处理步骤

1.加入处理json的工具库的依赖, springmvc默认使用jackson

2.把java对象转成json,我们使用一个标签,需要在springmvc的配置文件中加入

< mvc:annotation-driven >标签, 这个标签叫做注解驱动, 可以帮我们完成把java对象转成json

3.在处理器方法的上方加如一个叫做@ResponseBody的注解,可以帮我们完成输出的功能



