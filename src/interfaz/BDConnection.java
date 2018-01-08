package interfaz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BDConnection {
	private Connection connection = null;


	public BDConnection() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");


		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:src/BD/PVTranslator.db");

		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	protected void finalize() {
		try {
			if (connection != null)
				connection.close();

		} catch (SQLException e) { // Use SQLException class instead.
			System.err.println(e);
		}

	}

	public List<Object[]> Select(String sel)
	{
		ResultSet rset;
		List<Object[]> lista = new ArrayList<Object[]>();
		try
		{
			Statement stmt = connection.createStatement();
			rset = stmt.executeQuery(sel);
			ResultSetMetaData meta = rset.getMetaData();
			int numCol = meta.getColumnCount();
			while (rset.next())
			{
				Object[] tupla = new Object[numCol];
				for(int i=0; i<numCol;++i)
				{
					tupla[i] = rset.getObject(i+1);
				}
				lista.add(tupla);
			}
			rset.close();
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error en el SELECT: " + sel+ ". " + ex.getMessage());
		}		

		return lista;
	}

	public void Insert(String ins)
	{
		try
		{
			Statement stmt = connection.createStatement();
			stmt.execute(ins);
			stmt.close();
		}
		catch (SQLException ex)
		{
			if(ex.getErrorCode()==19) {
				JOptionPane.showMessageDialog(null, "Esta curva ya esta en la BD","Error!",JOptionPane.ERROR_MESSAGE);
			}else {
				throw new Error("Error en el INSERT: " + ins+ ". " + ex.getMessage());
			}
		}
	}

	public void Delete(String del)
	{
		try
		{
			Statement stmt = connection.createStatement();
			stmt.execute(del);
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error en el DELETE: " + del+ ". " + ex.getMessage());
		}
	}

	public void Update(String up)
	{
		try
		{
			Statement stmt = connection.createStatement();
			stmt.execute(up);
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error en el UPDATE: " + up+ ". " + ex.getMessage());
		}
	}

}
