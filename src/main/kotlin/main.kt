import java.sql.Connection
import java.sql.DriverManager

fun main(args: Array<String>) {
    var inicio= Inicio(emptyArray<String>().toMutableList(), emptyList<Int>().toMutableList(), args[0])
    if (inicio != null)
        inicio.inicio()
}

class Inicio(val numbres: MutableList<String>, val numero_Par: MutableList<Int>, val param: String) {

    fun inicio() {
        criartbelas()

        val CalcularNumeroPar = CalcNumPar()
        CalcularNumeroPar.cal(numbres, numero_Par)
    }

    fun conexao_banco(): Connection? {
        Class.forName("org.sqlite.JDBC")
        val conn = DriverManager.getConnection("jdbc:sqlite::memory:")
        return conn
    }

    fun criartabelas() {
        val conn: Connection? = conexao_banco()

        conn!!.let {
            it.createStatement().executeUpdate("create table if not exists sample(id, num)");
            it.createStatement().executeUpdate("insert into sample values(1, 1)");
            it.createStatement().executeUpdate("insert into sample values(2, 2)");
            it.createStatement().executeUpdate("insert into sample values(3, 3)");
            it.createStatement().executeUpdate("insert into sample values(4, 4)");
            val prepareStatement = it.prepareStatement("SELECT * FROM sample")
            prepareStatement.execute()
            val rs = prepareStatement.resultSet
            while (rs.next()) {
                // read the result set
                numero_Par.add(rs.getInt("num"))
            }
        }

        conn!!.let {
            it.createStatement().executeUpdate("create table if not exists sample2(id, num)");
            it.createStatement().executeUpdate("insert into sample2 values(1, 'one')");
            it.createStatement().executeUpdate("insert into sample2 values(2, 'two')");
            it.createStatement().executeUpdate("insert into sample2 values(3, 'three')");
            it.createStatement().executeUpdate("insert into sample2 values(4, 'four')");
            val prepareStatement = it.prepareStatement("SELECT * FROM sample2 WHERE num = ${param}")
            prepareStatement.execute()
            val rs = prepareStatement.resultSet
            while (rs.next()) {
                // read the result set
                numbres.add(rs.getString("num"))
            }
        }

//        conn?.let {
//            it.nativeSQL("CREATE TABLE IF NOT EXISTS numeros(\n" +
//                    "                id INTEGER PRIMARY KEY,\n" +
//                    "                num INTEGER NOT NULL")
//            it.nativeSQL("INSERT INTO numeros (1,2,3,4)")
//
//            val prepareStatement = it.prepareStatement("SELECT * FROM numeros")
//            print(prepareStatement)
//        }

    }
}

class CalcNumPar {
    // @TODO: Add logica
    fun cal(numbers: List<String>, numeros: List<Int>) {
        // For dos numeros
        for (n in numbers){
            if (n == null) continue
            // For dos numeros int
            for (t in numeros)      {
                if (t == null) {
                    continue
                }
                // Matematica loca pra ver se eh par
                val resultado = t % 2
                val ehPar = 0
                if (t % 2 == ehPar)
                {
                    if (t % 2 == 0) {
                        // Naum pode ce 2
                        if (t != 2) {
                            // @TODO: Refatorar somente esse bloco
                            print(t)
                        }
                        else {
                            // pass
                        }
                    }
                } else {
                    // pass
                }
            }
        }
    }
}