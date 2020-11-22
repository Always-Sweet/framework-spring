### Spring框架

---

#### 框架概述

1. **什么是Spring**

&emsp;&emsp;Spring Framework是一个开源的轻量级Java EE应用程序框架，由Rod Johnson在其著作《Expert One-On-One J2EE Development and Design》中阐述的部分理念和原型衍生而来。

&ensp;&ensp;&ensp;&ensp;它提供的一种简易的开发方式，避免了那些可能致使底层代码变得繁杂混乱的大量的属性文件和帮助类。框架的主要优势之一就是其分层架构，分层架构允许使用者选择其他为应用程序开发提供功能集成的框架。




2. **Spring的优点**

- 方便解耦，简化开发

Spring就是一个大工厂，可以将所有对象创建和依赖的关系维护，交给Spring管理。

- AOP编程的支持

Spring提供面向切面编程，可以方便的实现对程序进行权限拦截、运行监控等功能。

- 声明式事务的支持

只需要通过配置就可以完成对事务的管理，而无需手动编程。

- 方便程序的测试

Spring对Junit4支持，可以通过注解方便的测试Spring程序。

- 方便集成各种优秀框架

Spring不排斥各种优秀的开源框架，其内部提供了对各种优秀框架的直接支持（如：Struts、Hibernate、MyBatis等）。

- 降低JavaEE API的使用难度

Spring对JavaEE开发中非常难用的一些API（JDBC、JavaMail、远程调用等），都提供了封装，使这些API应用难度大大降低。



3. **Spring的体系结构**

&emsp;&emsp;Spring框架是一个分层架构，它包含了一系列的功能要素并被分为多个模块，包括：核心容器（Core Container）、数据访问/集成（Data Access/Integration）层、Web层、AOP（Aspect Oriented Programming）模块、植入（Instrumentation）模块、消息传输（Messaging）和测试（Test）模块。



![Spring Framework](\image\spring-framework.png)



#### 控制反转（IOC）

&emsp;&emsp;在传统Java EE程序中，当我们需要使用一个对象的时候通常是通过new进行对象的创建，而控制反转则是改变了对象的创建来源，由IOC容器控制对象的创建。



**代码实现**

添加依赖

核心（core，context，beans，expression）

使用maven依赖


```maven
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.2.11.RELEASE</version>
</dependency>
```

创建一个Service类，这里以DicService举例

```Java
public class DicService {}
```

配置文件applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dicService" class="com.demo.ioc.DicService"></bean>
</beans>
```

测试

```Java
public class SpringStarter {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DicService dicService = (DicService) applicationContext.getBean("dicService");
    }
}
```



#### 依赖注入（DI）

&emsp;&emsp;由容器动态的将某个依赖关系注入到组件之中，提了升组件重用的频率，并为系统搭建一个灵活、可扩展的平台。通过依赖注入机制，我们只需要通过简单的配置，而无需任何代码就可指定目标需要的资源，完成自身的业务逻辑，而不需要关心具体的资源来自何处，由谁实现。



依赖注入方式分为：xml装配和注解装配



**xml装配方式：**

1. 属性（seting）注入

为DicService添加一个属性DicDao，并且**添加setting方法**，这个很重要！！！

```Java
public class DicService {

    DicDao dicDao;
    
    public void setDicDao(DicDao dicDao) {
        this.dicDao = dicDao;
    }
}
```

在配置文件中对DicService配置属性注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dicDao" class="com.demo.ioc.DicDao" />
    <bean id="dicService" class="com.demo.ioc.DicService">
        <!-- 属性注入 -->
        <property name="dicDao" ref="dicDao" />
    </bean>

</beans>
```



2. 构造器（constructor）注入

通过构造器注入一个名称

```Java
public class DicService {

    String name;

    public DicService(String name) {
        this.name = name;
    }
}
```

在配置文件中通过构造函数注入名称

```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dicService" class="com.demo.DicService">
        <!-- 构造器注入 -->
        <constructor-arg name="name" value="myName" />
    </bean>

</beans>
```



2. 集合注入

集合注入则是在属性为集合对象时使用

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="bean" class="com.demo.Bean">
        <!-- Array数组 -->
        <property name="arrayData">
            <array>
                <value>1</value>
                <value>2</value>
                <value>3</value>
            </array>
        </property>
        <!-- List数组 -->
        <property name="listData">
            <list>
                <value>1</value>
                <value>2</value>
                <value>3</value>
            </list>
        </property>
        <!-- Set集合 -->
        <property name="setData">
            <set>
                <value>1</value>
                <value>2</value>
                <value>3</value>
            </set>
        </property>
        <!-- Map集合 -->
        <property name="mapData">
            <map>
                <entry key="jack" value="杰克"></entry>
                <entry>
                    <key><value>rose</value></key>
                    <value>肉丝</value>
                </entry>
            </map>
        </property>
        <!-- Properties属性 -->
        <property name="propsData">
            <props>
                <prop key="a">1</prop>
                <prop key="b">2</prop>
            </props>
        </property>
    </bean>

</beans>
```



**注解装配**

通过对需要注入的属性进行注解修饰，无需xml配置，开发中往往通过注解简化配置流程。

For example：

> @Component取代<bean class="">



依赖注入

- 普通值：@Value("")
- 引用值：

@Autowired：默认按照类型注入

@Resource：默认按照名称注入，该注解有两个属性name和type，表明可以通过配置属性来指定通过名称还是类型注入

@Qualifier(value="")：按照类型注入bean id等于value值的对象



#### 面向切面编程（AOP）

这种在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程。



**AOP实现原理**

- aop底层将采用代理机制进行实现。
- 接口 + 实现类 ：spring采用 jdk 的动态代理Proxy。
- 实现类：spring 采用 cglib字节码增强。



**AOP术语**

1. target：目标类，需要被代理的类。
2. Joinpoint（连接点）：所谓连接点是指那些可能被拦截到的方法。
3. PointCut 切入点：已经被增强的连接点。例如：addUser()
4. advice 通知/增强，增强代码。例如：after、before
5. Weaving（织入）：是指把增强advice应用到目标对象target来创建新的代理对象proxy的过程。
6. proxy 代理类
7. Aspect（切面）： 是切入点pointcut和通知advice的结合

![AOP](\image\aop.png)



**代码实现**

手动方式：JDK动态代理/CGLIB字节码增强



JDK动态代理

> JDK动态代理 对“装饰者”设计模式 简化。使用前提：必须有接口



1. 目标类：接口+实现类

```Java
public interface DicService {

    public void addDic();
    public void updateDic();
    public void deleteDic();
}
```

2. 切面类：用于存放通知

```Java
public class MyAspect {

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }
}
```

3. 工厂类：生成代理

```Java
public class MyBeanFactory {

    public static DicService createDicService() {
        // 目标类
        final DicService dicService = new DicServiceImpl();
        // 切面类
        final MyAspect myAspect = new MyAspect();
        /**
         * 代理类，将目标类（切入点）和切面类（通知）结合 --> 切面
         */
        DicService proxService = (DicService) Proxy.newProxyInstance(MyBeanFactory.class.getClassLoader(), dicService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 前置方法执行
                myAspect.before();
                // 目标方法执行
                Object obj = method.invoke(dicService, args);
                // 后置方法执行
                myAspect.after();
                return obj;
            }
        });

        return proxService;
    }
}
```

测试

```Java
public class SpringAOPStarter {

    public static void main(String[] args) {
        DicService dicService = MyBeanFactory.createDicService();
        dicService.addDic();
        dicService.updateDic();
        dicService.deleteDic();
    }
}

输出：
before
addDic……
after
before
updateDic……
after
before
deleteDic……
after
```



**CGLIB字节码增强**

- 没有接口，只有实现类。
- 采用字节码增强框架 cglib，在运行时创建目标类的子类，从而对目标类进行增强。

```Java
public class MyBeanFactoryCGLIB {

    public static DicServiceImpl createDicService() {
        // 目标类
        final DicServiceImpl dicService = new DicServiceImpl();
        // 切面类
        final MyAspect myAspect = new MyAspect();
        /**
         * 代理:CGLIB字节码增强
         */
        // 核心类
        Enhancer enhancer = new Enhancer();
        // 确定父类
        enhancer.setSuperclass(dicService.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                // 前置方法执行
                myAspect.before();
                // 目标方法执行
                Object obj = methodProxy.invokeSuper(proxy, args);
                // 后置方法执行
                myAspect.after();
                return obj;
            }
        });
        // 创建代理
        DicServiceImpl proxService = (DicServiceImpl) enhancer.create();
        return proxService;
    }
}
```



**半自动实现**

> 让spring创建代理对象，从spring容器中手动的获取代理对象

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 目标类 -->
    <bean id="dicService" class="com.demo.aop.DicServiceImpl"></bean>
    <!-- 切面类 -->
    <bean id="myAspect" class="com.demo.aop.MyAspectImpl"></bean>
    <!-- 代理类 -->
    <bean id="proxService" class="org.springframework.aop.framework.ProxyFactoryBean">
        <property name="interfaces" value="com.demo.aop.DicService" />
        <property name="target" ref="dicService" />
        <property name="interceptorNames" value="myAspect" />
    </bean>

</beans>
```

测试

```jAVA
public static void main(String[] args) {
    String xmlPath = "applicationContextSemiAutomaticAop.xml";
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
    DicService semiAutoProxService = (DicService) applicationContext.getBean("proxService");
    semiAutoProxService.addDic();
    semiAutoProxService.updateDic();
    semiAutoProxService.deleteDic();
}
```



**全自动实现**

> 从spring容器获得目标类，如果配置aop，spring将自动生成代理。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 目标类 -->
    <bean id="dicService" class="com.demo.aop.DicServiceImpl"></bean>
    <!-- 切面类 -->
    <bean id="myAspect" class="com.demo.aop.MyAspectImpl"></bean>
    <!-- AOP
        1.导入命名空间
        xmlns:aop="http://www.springframework.org/schema/aop"
        2.使用<aop:config>进行配置
        proxy-target-class="true"声明代表使用cglib代理
        <aop:pointcut> 切入点
        <aop:advisor> 切面
        3.切入点表达式
        execution(* com.demo.*.*(..)))
        返回任意值 包名 任意类 任意方法 任意参数
    -->
    <aop:config proxy-target-class="true">
        <aop:pointcut id="pointcut" expression="execution(* com.demo.aop.*.*(..)))" />
        <aop:advisor advice-ref="myAspect" pointcut-ref="pointcut" />
    </aop:config>

</beans>
```

测试


```Java
public static void main(String[] args) {
    // 全自动配置代理
    String xmlPath2 = "applicationContextFullyAutomatedAop.xml";
    ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext(xmlPath2);
    DicService fullyAutoProxService = (DicService) applicationContext2.getBean("dicService");
    fullyAutoProxService.addDic();
    fullyAutoProxService.updateDic();
    fullyAutoProxService.deleteDic();
}
```