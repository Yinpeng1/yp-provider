# yp-provider
#springboot形式的dubbo服务提供者
#整合了多数据源和多redis实例
#如果某个service的某个方法需要同时注入多个数据源并且对多数据源进行非读操作，就需要把多数据源交给automic来管理控制事物，否则无需开启atomic，直接注入多数据源，并开启本数据源的事物即可
#提供服务的service注入其他数据源的service和mapper都可以，只要在调用方法上加上@Transactional即可控制多数据源的事物
