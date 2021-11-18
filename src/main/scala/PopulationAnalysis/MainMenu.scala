package PopulationAnalysis

import scala.io.StdIn.readInt

object MainMenu extends App {

  def menu():Int={
    println("MainMenu")
    println("********")
    print("1. Administration \n2. Data Analysis \n3. Data Visualization \n0. Exit \n ..Select your choice: ")
    readInt();
  }

  def main(): Unit =
  {   var menuSelection =0;
    do{
      menuSelection = menu()
      menuSelection match {
        case 1 => Admin.administration()
        case 2 => AnalysisHome.dataAnalysis()
        case 3 => Visualize.dataVisualization()
        case 0 => println("Exiting....")
      }
    }while (menuSelection !=0)
  }
  main()
}