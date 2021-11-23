package PopulationAnalysis

import scala.io.StdIn.readInt
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions.{lower, upper}


object AnalysisHome extends App {
  def dataAnalysis(): Unit =
  {
    val spark = SparkSession
      .builder()
      .appName("Hello Hive")
      .config("spark.master", "local")
      //.enableHiveSupport()
      .getOrCreate()
    val sc = spark.sparkContext
    spark.sparkContext.setLogLevel("ERROR")
    val df = spark.read.option("header","true").option("inferSchema", "true").csv("data/WPP2019_TotalPopulationBySex.csv")
    df.persist(StorageLevel.MEMORY_ONLY)

    var menuSelection = 0;
    do{
      menuSelection = AnalysisMenu()
      menuSelection match {
        case 1 =>{
          df.show()
          //df.persist(StorageLevel.MEMORY_ONLY)
        } // Please add query
        case 2 => { //variant set to medium because there's like 5-7 versions of predictive populations and we only need 1
          val df2 = df.where(df("Time") < "2022").where(df("Variant") === "Medium").groupBy("Time").sum("PopTotal").sort(df("Time").desc) // shows worldwide populations
          df2.show(false)
          //df2.write.option("header", true).format("csv").option("SaveMode", "overwrite").save("data/case2.csv")
        }// Please add Query
        case 3 => {
          print("Population of Which Country: ")
          val country = readLine().toLowerCase // both column entry and user input are set to lowercase to ignore case of dataset
          val df2 = df.where(df("Time") < "2022").where(lower(df("Location")).like(s"%$country%")).where(df("Variant") === "Medium").select("Time", "Location", "PopTotal", "PopMale", "PopFemale").sort(df("Time").desc, df("Location")) // shows worldwide populations
          df2.show(500, false)
         // df2.write.option("header", true).csv("data/case3")
        }// Please add query
        case 4 => {}// Please add query - Gerardo's Query
        // shows the increase rate for each contry in % for males and females since 1950 
        df.createOrReplaceTempView("population")
         spark.sql("select location, concat(round((avg(popmale)-min(popmale))/min(popmale)*100,2), '%')" +
            " as increase_rate_male, concat(round((avg(popfemale)-min(popfemale))/min(popfemale)*100,2), '%')" +
            " as increase_rate_female from population where time between '1950' and '2020' group by location having count(location)>1 order by location asc ").show()  
        
        }// Please add query
        case 5 => {}// Please add query
        case 6 => {}// Please add query
        case 7 => {}// Please add query
        case 0 => {println("Exiting Data Visualization....")}
      }
    }while (menuSelection !=0 )
  }

  def AnalysisMenu(): Int ={
    // Need these to be updated with the query names and visualize calling methods in Visualize object.
    //Need this menu to be updated

    println("Analysis Menu \n************")
    print(" 1. Select all  \n 2. World by year \n 3. growth of a country by year \n 4. Gerardo's Query\n 5. \n 6. \n 7.  \n"+
      " 8. \n 0. Exit \n ..Select your choice:  ")
    print(" 1. Select all  \n 2. World by year \n 3. growth of a country by year \n 4. Increase rate by country from 1950 to 2020(male and female) \n 5. \n 6. \n 7.  \n 8. \n 0. Exit \n ..Select your choice:  ")
    readInt()
  }
}
