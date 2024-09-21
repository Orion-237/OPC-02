import java.util.InputMismatchException
import java.util.Scanner


open class Client(val name: String,val meterNumber: Int, val consumption: Double){
    open val payRate = 50
    open fun calculateBill() = if (consumption>1000) 0.9*consumption*payRate else consumption*payRate
    open val bill = calculateBill()
}

class PrivateClient(name: String, meterNumber: Int, consumption: Double): Client(name, meterNumber, consumption){
    override val payRate: Int = 45
    override val bill: Double = super.calculateBill()
}

class ClientCompany(name: String, meterNumber: Int, consumption: Double): Client(name, meterNumber, consumption){
    override val payRate: Int = 55
    override val bill: Double = super.calculateBill()
}

var clients: MutableList<Client> = mutableListOf()

//fun createPrivate(name: String, meterNumber: Int, consumption: Double){
//    clients.add(PrivateClient(name,meterNumber, consumption)
//}
//
//fun createCompany(name: String, meterNumber: Int, consumption: Double){
//    clients.add(ClientCompany(name, meterNumber, consumption))
//}

fun main(){

    val reader = Scanner(System.`in`)
    var nextClient = true
    var name: String
    var meterNumber: Int
    var consumption = 0.0
    var currentClient: Client
    var answer = ""
    var sum=0.0
    var average: Double

    while (nextClient){
        println("Bienvenue a l'application de facturation d'electricite\n")

        println("What would you like to do?")
        println("1 - Create Private Client")
        println("2 - Create Company Client")
        println("3 - Display bills")
        println("4 - Display average consumption")

        print("\nWhat's your name?\t")
        name = reader.nextLine()
        println()

        print("What's your meter number?\t")
        meterNumber = reader.nextInt()
        println()

        try {
            print("What's your consumption? (in kWh)\t")
            consumption = reader.nextDouble()
            println()
            if (consumption<0) throw ArithmeticException("Consumption can't be negative")
        }
        catch (e: ArithmeticException){
            while (consumption<0){
                println("Consumption can't be negative")
                print("What's your consumption? (in kWh)\t")
                consumption = reader.nextDouble()
                println()
            }
        }
        currentClient = Client(name,meterNumber,consumption)
        clients.add(currentClient)

        println("Hello, ${name.capitalize()}.")
        println("Your meter number is $meterNumber and your consumption is $consumption kWh")

        println("Hence, your bill is ${currentClient.bill}frs\n")
//        bills.add(bill)

        print("Is there another Client? (Y/N)\t")
        answer = reader.nextLine()

        while (answer!= "Y" && answer!="y" && answer!="n" && answer!="N" ){
//            print("Is there another Client? (Y/N)\t")
            answer = reader.nextLine()
        }

        if (answer=="n" || answer=="N") nextClient=false
    }

    for (client in clients){
        sum += client.bill
    }
    average = sum/clients.size
    println("\nThe average consumption is ${average}frs\n")






}
