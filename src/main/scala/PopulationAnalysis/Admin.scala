package PopulationAnalysis

import PopulationAnalysis.MainMenu.menu

import java.sql.{Connection, DriverManager, Statement}
import scala.io.StdIn.{readInt, readLine}

object Admin extends App {

  def createTablePopulation(): Unit ={

  }

  def loadClassData(): Unit ={

  }

  def administration(): Unit ={
    var menuSelection =0;
    do{
      menuSelection = adminMenu()
      menuSelection match {
        case 1 => createAdmin()
        case 2 => changePassword()
        case 3 => PopulateData()
        case 0 => println("Returning to Main Menu....")
      }
    }while (menuSelection !=0)

  }
  def adminMenu(): Int =
  {
      println("Admin Menu")
      println("********")
      print("1. Create User/Admin \n2. Change Password \n3. Load Data in to Tables \n0. Return to MainMenu  \n ..Select your choice: ")
      readInt();
  }
  def createAdmin(): Unit ={
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/RevP2"
    val userName = "root"
    val password ="RootPass1"

    try {
      Class.forName(driver)
      val connection: Connection = DriverManager.getConnection(url, userName, password)
      val statement: Statement = connection.createStatement()
      println(statement.executeQuery("show databases"))

      println("What is the Admin username you want to create")
      val createUserName = readLine()
      println("Please set the default passwd")
      val createPasswd = readLine()
      println("Do you want to make this user Admin [Yes/No]" )
      val createauthType = checkFlag(readLine())

      val addNewUserStr = "insert into AuthTable (userid, password, AdminFlag) values(\""+ createUserName +"\",\"" + createPasswd +"\","+ createauthType +")"
      println(addNewUserStr)

      //      val is = statement.execute("insert into AuthTable (userid, password, AdminFlag) values("+ createUserName +"," + createPasswd +","+ createauthType +")")
      val insNewuser = statement.execute(addNewUserStr)
      if (insNewuser == true)
        println("New user created successfully")
      connection.close()

    }catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      println("Press Enter to close DB Connection")
    }
  }

  def changePassword(): Unit ={

    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/RevP2"
    val userName = "root"
    val password ="RootPass1"

    try {
      Class.forName(driver)
      val connection: Connection = DriverManager.getConnection(url, userName, password)
      val statement: Statement = connection.createStatement()

      println("Enter the username you want to change password for")
      val uName = readLine()
      println("Please enter the old password")
      val oldpass = readLine()
      println("Please choose New password")
      val newPass = readLine()
      println("Do you want to make this user Admin [Yes/No]" )
      val updAuthType = checkFlag(readLine())
      println(updAuthType)
      val updateString = "update AuthTable Set password= \""+ newPass+ "\", AdminFlag =" +  updAuthType + " Where userid = \""+ uName + "\" and password = \"" + oldpass+"\""
      println(updateString)
      val insNewuser = statement.execute(updateString)

      if (insNewuser == true)
        println("Your password changed successfully")
      connection.close()

    }catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      println("Press Enter to close DB Connection")
    }
  }

  def PopulateData(): Boolean ={
    val loaded = true
       if (loaded) true else false
  }
  def checkFlag(flagString :String): Boolean={
    var flgBool = false
    if (( flagString.toUpperCase() == "YES")) {
        flgBool = true
    } else if((flagString.toUpperCase()== "NO"))
    {
       flgBool = false
    }
    flgBool
  }
}
