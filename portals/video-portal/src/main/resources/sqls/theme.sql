#namespace("user")
  #sql("queryUser")
    select * from user t where t.User like concat('%',#para(title),'%')
  #end
#end
