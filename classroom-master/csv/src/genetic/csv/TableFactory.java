package genetic.csv;

public class TableFactory
{
	public static final String[] TOKENS = 
	{
		"ENROLLMENT"
	,	"CLASS"
	,	"DISTANCE"
	};

	public static Table make(String token)
	{
		if(token.equals(TOKENS[0]))
			return new EnrollmentTable();

		if(token.equals(TOKENS[1]))
			return new ClassTable();	

		if(token.equals(TOKENS[2]))
			return new DistanceTable();
	
		return null;
	}
}
