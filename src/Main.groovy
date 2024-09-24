@Grab(group='org.xerial', module='sqlite-jdbc', version='3.36.0.3')
import groovy.sql.Sql
import groovy.lang.GroovyObject
import groovy.lang.GroovyClassLoader
import nv.common.database.DBQueryRunner

static void main(String[] args) {
//    // 定义 SQLite 数据库的路径
//    def dbPath = 'C:/Users/SAHerbHuangT14/initial-db.sqlite'
//
//    // 连接到 SQLite 数据库
//    def url = "jdbc:sqlite:$dbPath"
//    def sql = Sql.newInstance(url, "org.sqlite.JDBC")
//
//    sql.eachRow('SELECT * FROM sample_user') { row ->
//        println "id: ${row.id}, name: ${row.name}, age: ${row.age}"
//    }
//
//    sql.close()

//    query(, 'SELECT * FROM sample_user')

}

//def static query(Class<E> clazz, String sql) {
//
//    def dbPath = 'C:/Users/SAHerbHuangT14/initial-db.sqlite'
//
//    // 连接到 SQLite 数据库
//    def url = "jdbc:sqlite:$dbPath"
//    def sqlInstance = Sql.newInstance(url, "org.sqlite.JDBC")
//
//    def resultList = []
//    sqlInstance.eachRow(sql) { row ->
//        // 创建 clazz 对象实例
//        def instance = clazz.newInstance()
//
//        // 遍历类的所有字段并填充数据
//        clazz.declaredFields.each { Field field ->
//            field.setAccessible(true)  // 如果字段是私有的，允许访问
//            def columnName = field.name
//
//            // 将结果集的对应列值赋给对象字段
//            if (row.containsKey(columnName)) {
//                field.set(instance, row[columnName])
//            }
//        }
//        resultList << instance  // 将对象添加到结果列表
//    }
//
//    sqlInstance.close()
//    return resultList
//}
