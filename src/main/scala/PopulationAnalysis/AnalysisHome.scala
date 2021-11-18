package PopulationAnalysis

import scala.io.StdIn.readInt

object AnalysisHome extends App {
  def dataAnalysis(): Unit =
  {
    var menuSelection = 0;
    do{
      menuSelection = AnalysisMenu()
      menuSelection match {
        case 1 =>{} // Please add query
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
