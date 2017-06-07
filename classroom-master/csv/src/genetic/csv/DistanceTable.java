package genetic.csv;

public class DistanceTable extends Table
{
	public DistanceTable()
	{
		
	}

	public Table selectRows(String attribute, String value)
	{
		Table table = TableFactory.make("DISTANCE");

		for(CSVElement row : rows)
		{
			if(row.getElement(attribute).equals(value))
				table.insertRow(row);
		}

		return table;
	}

	public int getDistance(String from, String to)
	{
		for(CSVElement row : rows)
		{
			if(row.getElement("classroom").equals(from))
			{
				try{
				return Integer.parseInt(row.getElement(to));
				}catch(Exception e){}
			}
		}

		return 0;
	}
}
