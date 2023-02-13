import java.util.*;
public class Main {

static int[] array;

public static double dist(double x1 , double y1 , double x2 , double y2) {
	double base = x2 - x1;
	double height = y2 - y1;
	
	return Math.hypot(base , height);
}

public static int find(int n) {
	return array[n] == n ? n : (array[n] = find(array[n]));
}

public static void merge(int i , int j) {
	array[find(i)] = find(j);
}

public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

int runTime = scanner.nextInt();

while (runTime > 0)
	{
	int points = scanner.nextInt();
	double[] x = new double[points];
	double[] y = new double[points];
	
	for (int i = 0; i < points; i++)
		{
		x[i] = scanner.nextDouble();
		y[i] = scanner.nextDouble();
		}
	
	ArrayList<Adjacency> adjcs = new ArrayList<>();
	
	for (int i = 0; i < points; i++)
		for (int j = i + 1; j < points; j++)
			adjcs.add(new Adjacency(i , j , dist(x[i] , y[i] , x[j] , y[j])));
	
	Collections.sort(adjcs);
	
	array = new int[points];
	
	for (int i = 0; i < points; i++)
		array[i] = i;
	
	int count = 0;
	double sum = 0;
	
	while (count < points - 1)
		{
		Adjacency adjc = adjcs.remove(0);
		
		if (find(adjc.node1) != find(adjc.node2))
			{
			merge(adjc.node1 , adjc.node2);
			sum += adjc.distance;
			count++;
			}
		}
	
	System.out.println(sum);
	runTime--;
	}

scanner.close();
	}
}

class Adjacency implements Comparable<Adjacency> {
	
int node1 , node2;
double distance;

public Adjacency(int a , int b , double x) {
	node1 = a;
	node2 = b;
	distance = x;
}

public int compareTo(Adjacency adjc) {
	double difference = this.distance - adjc.distance;
	
	if (difference > 0)
		return 1;
	
	if (difference == 0)
		return 0;
	
	return -1;
}



}
