package PopulationAnalysis

import scala.io.StdIn.readInt
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel

object AnalysisHome extends App {
  val DATA_FILE = "Data/WPP2019_PopulationBySingleAgeSex_1950-2019.csv"
  def dataAnalysis(): Unit =
  {
    val spark = SparkSession
      .builder()
      .appName("Hello Hive")
      .config("spark.master", "local")
      .enableHiveSupport()
      .getOrCreate()
    val sc = spark.sparkContext
    spark.sparkContext.setLogLevel("ERROR")
    var menuSelection = 0;
    do{
      menuSelection = AnalysisMenu()
      menuSelection match {
        case 1 =>{
          val df = spark.read.option("header","true").option("inferSchema", "true").csv(DATA_FILE)
          df.persist(StorageLevel.MEMORY_ONLY)
          df.where(df("Time") === "2019").groupBy("LocID", "Location", "Time").avg("PopMale", "PopFemale").sort("Time").show(false)
          df.groupBy("Time").sum("PopMale", "PopFemale").sort(df("Time").desc).show(false) // shows worldwide populations
        } // Please add query
        case 2 => {}// Please add Query
        case 3 => {}// Please add query
        case 4 => {}// Please add query
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
    print(" 1. Select all  \n 2. World by year \n 3. growth of a country by year \n 4. \n 5. \n 6. \n 7.  \n 8. \n 0. Exit \n ..Select your choice:  ")
    readInt()
  }
}
