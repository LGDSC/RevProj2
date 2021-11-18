package PopulationAnalysis

import scala.io.StdIn.readInt
object Visualize extends App {

    def dataVisualization(): Unit ={
        var menuSelection = 0;
        do{
            menuSelection = visualizeMenu()
            menuSelection match {
                case 1 => {} // Please add query
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

    def visualizeMenu(): Int ={
        //print("1. Bar graph  \n2. WorldMap \n3. Pie  \n 4. \n0. Exit \n ..Select your choice: ")
        println("Visualization Menu\n************")
        print(" 1. Bar Chart/Graph. \n 2. Pie Chart \n 3. Line Graph or Chart \n 4. Histogram Chart  \n 5. Area Chart \n 6. Dot Graph or Plot \n 7. Scatter Plot \n 8. Bubble Chart \n\n ..Select your choice: ")
        readInt()
    }

}
