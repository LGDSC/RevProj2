package PopulationAnalysis


import java.sql.{Connection, DriverManager, Statement}
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine

object login extends App {

  def verifyLogin()
  {
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/RevP2"
    val userName = "root"
    val password ="RootPass1"

    println("please enter your username")
    val uName = readLine()
    println("Enter your password")
    val passwd = readLine()

    println(uName, passwd)


    try {

      Class.forName(driver)
      println(1)
      val connection: Connection = DriverManager.getConnection(url, userName, password)
      println(connection)
      val statement: Statement = connection.createStatement()
      println(statement.executeQuery("show databases"))

      val verifyStr = "select * from AuthTable where ((userid= \'"+ uName+ "\') and (password = \'"+passwd+"\'))"
      println(verifyStr)

      val rs = statement.executeQuery(verifyStr)
      rs.next()
      var adminFlag: Boolean = rs.getBoolean("AdminFlag")
      println(rs.getRow)
      val rsusrs = rs.getString("userid")
      println(rsusrs)

      if (adminFlag == true)
      {
        print("MainMenu.main()")
        MainMenu.main()
      }
      else {
        println("This login does not exist, You are previleged to let you view Covid Data analysis as guest as Guest")
      }

      connection.close()

    }catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      println("Press Enter to close DB Connection")
    }

  }
  verifyLogin()
}
