import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		boolean error = false;
		String sql;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new Date(System.currentTimeMillis());

		Scanner entrada = new Scanner(System.in);

		System.out.println("ID");
		String id = entrada.nextLine();

		System.out.println("Nom");
		String nom = entrada.nextLine();

		System.out.println("Cognom");
		String cognom = entrada.nextLine();

		System.out.println("Ofici");
		String ofici = entrada.nextLine();

		System.out.println("ID del Director");
		String director = entrada.nextLine();

		System.out.println("Salari");
		String salari = entrada.nextLine();

		System.out.println("Comisio");
		String comisio = entrada.nextLine();

		System.out.println("Nom del Departament");
		String nomDepartament = entrada.nextLine();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/M6?useSSL=false", "root", "123");
			
			Statement sentencia1 = conexion.createStatement();
			Statement sentencia2 = conexion.createStatement();
			Statement sentencia3 = conexion.createStatement();

			ResultSet existeix = sentencia1
					.executeQuery("SELECT * FROM departamentos where nombre like '" + nomDepartament + "';");
			ResultSet empleatsID = sentencia2
					.executeQuery("SELECT * FROM empleados where id = " + id + ";");
			ResultSet dir = sentencia3
					.executeQuery("SELECT * FROM empleados where id = " + Integer.parseInt(director) + ";");

			if (!existeix.next()) {
				error = true;
				System.out.println("Error, el departament no existeix");
			}

			if (empleatsID.next()) {
				error = true;
				System.out.println("Error, el id insertat ja existeix");
			}

			if (Float.parseFloat(salari) < 0) {
				error = true;
				System.out.println("Error, el salari ha de ser superior a 0");
			}

			if (!dir.next()) {
				error = true;
				System.out.println("Error, el director no existeix");
			}

			if (!error) {
				sql = "Insert into empleados VALUES(" + Integer.parseInt(id) + ", '" + nom + "', '" + cognom + "', '"
						+ ofici + "', " + Integer.parseInt(director) + ", " + Float.parseFloat(salari) + ", "
						+ Float.parseFloat(comisio) + ", '" + nomDepartament + "', '" + data + "', " + existeix.getInt(1) + ")";
				int files = sentencia3.executeUpdate(sql);
				System.out.println("Files actualitzades: " + files);
			}

			existeix.close();
			empleatsID.close();
			dir.close();
			sentencia1.close();
			sentencia2.close();
			sentencia3.close();
			conexion.close();

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Escriu el id del departament que vols saber: ");
		String opcio = entrada.nextLine();
		
		String sentencia1 = "select cognom, salari, ofici, nomDepartament from empleados where id = ?;";
		String sentencia2 = "select AVG(salari), COUNT(ofici) from empleados where id = ?;";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/M6?useSSL=false", "root", "123");
			
			PreparedStatement sentenciaSQL1 = conexion.prepareStatement(sentencia1);
			sentenciaSQL1.setInt(1, Integer.parseInt(opcio));
			ResultSet rs1 = sentenciaSQL1.executeQuery();
			
			while(rs1.next()) {
				String nombre = rs1.getString(1);
				float salario = rs1.getFloat(2);
				String oficio = rs1.getString(3);
				String nomDep = rs1.getString(4);
				System.out.println("Nom: " + nombre + " Salari: " + salario + " Ofici: " + oficio + " Nom Departament: " + nomDep);
			}
			
			PreparedStatement sentenciaSQL2 = conexion.prepareStatement(sentencia2);
			sentenciaSQL2.setInt(1, Integer.parseInt(opcio));
			ResultSet rs2 = sentenciaSQL2.executeQuery();
			
			while(rs2.next()) {
				float avg = rs2.getFloat(1);
				int count = rs2.getInt(2);
				System.out.println("AVG: " + avg + " Count:" + count);
			}
			
			rs1.close();
			rs2.close();
			sentenciaSQL1.close();
			sentenciaSQL2.close();
			conexion.close();
			
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
