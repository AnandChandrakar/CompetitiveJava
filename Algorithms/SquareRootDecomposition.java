import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;
/*
 * Author    : joney_000[let_me_start]
 * Algorithm : Sqrt Decomposition
 * Platform  : https://www.hackerrank.com/contests/w12/challenges/white-falcon-and-tree
 *
 */
class Query implements Comparable<Query>{
	
	int l,r,id;
	int  N ;
	int  sqrtN; 
	public Query(){}
	public Query(int l , int r , int id , int N , int sqrtN){
		this.id = id;
		this.l = l;
		this.r = r;
		this.id = id;
		this.N = N;
		this.sqrtN = sqrtN;

	}
	//first sort ascending block order ther ascending r order
	public int compareTo(Query q){
		if((this.l / this.sqrtN) < (q.l/this.sqrtN)){
			return -1;
			
		}else if((this.l / this.sqrtN) > (q.l/this.sqrtN)){
		
			return 1;
		}else {
			
			if(this.r < q.r)return -1;
			else if(this.r > q.r)return 1;
			else return 0;
		}
	}
	@Override
	public String toString(){
		return "L = "+this.l + " R = "+this.r + " idx = "+this.id;
	}

}
 
/*    The Main Class                */
class A
{	
    	private InputStream inputStream ;
	private OutputStream outputStream ;
	private FastReader in ;
    	private PrintWriter out ;
	/*
		Overhead [Additional Temporary Strorage] but provides memory reusibility for multiple test cases.
		Size Limit : 10^5 + 4 
	*/
	private final int BUFFER = 100005;
	private int    tempints[] = new int[BUFFER];
	private long   templongs[] = new long[BUFFER];
	private double tempdoubles[] = new double[BUFFER];
	private char   tempchars[] = new char[BUFFER];
	private final long mod = 1000000000+7;
	private final int  INF  = Integer.MAX_VALUE / 10;
	private final long INF_L  = Long.MAX_VALUE / 10;
	
	public A(){}
	public A(boolean stdIO)throws FileNotFoundException{
		
		if(stdIO){
			inputStream = System.in;
			outputStream = System.out;
		}else{
			inputStream = new FileInputStream("input.txt");
			outputStream = new FileOutputStream("output.txt");
		}
		in = new FastReader(inputStream);
		out = new PrintWriter(outputStream);
		
	}
      // fine place for Global Variables
	
	int ans[] = new int[200005];
	Query q[] = new Query[200005];
	int arr[];  	
	int res = 0;int n = 0;
	int cnt[]= new int[1000001];// HashMap
  	int currL = 1 ; int currR = 0;//currR = 0 to add first element
	int L = 0 ; int R = 0;
  	void run()throws Exception{
	
	//	 int tests = i();
	//	 once();
	//	 for(int t  = 1 ; t<= tests ; t++){
		  n = i(); 
		  arr = is(n);
		  int Q = i();

		  
		  for(int  i =1 ; i<= Q ;i++){
			int l = i(); int r = i();
			q[i] = new Query(l , r, i , n ,(int)Math.sqrt(n));		  	
		  }
		  
		  Arrays.sort(q , 1, Q+1);
		  

		//  for(int i = 1 ; i<= Q ; i++)print_r(q[i]);
		  
		  for(int i =1 ; i <= Q ; i++){
		  	L = q[i].l; R = q[i].r;
		  	while(currL < L){
		  		remove(currL);
		  		currL++;
		  	}
		  	while(currL > L){
		  		currL--;
		  		add(currL);
		  	}
		  	while(currR < R){
		  		currR++;
		  		add(currR);
		  	}
		  	while(currR > R){
		  		remove(currR);
		  		currR--;
		  	}
		  	ans[q[i].id] = res;
		  }
		  
		  for(int i = 1 ; i<= Q ; i++){out.write(""+ans[i]+"\n");out.flush();}
		 	
	//	}//end tests
	}//end run
	void add(int idx){
		if((idx > n) ||(idx <= 0 ))return;
		cnt[arr[idx]]++;
		if(cnt[arr[idx]]==1)res++;
		
	//	out.write("currL="+currL+" currR="+currR+" ans = "+res+"\n");
	}
	
	void remove(int idx){
		if((idx > n) ||(idx <= 0 ))return;
		cnt[arr[idx]]--;
		if(cnt[arr[idx]]==0)res--;
	//	out.write("currL="+currL+" currR="+currR+" ans = "+res+"\n");
	}
	
	void once(){
		
	}	
	void clear(int n){
	}
//****************************** My Utilities ***********************//
 	void print_r(Object... o){
       	out.write("\n"+Arrays.deepToString(o)+"\n");
        	out.flush();
	}

 	boolean isPrime(long n){
  		if(n==1)return false;
  		if(n<=3)return true;
  		if(n%2==0)return false;
  		for(int i=2 ;i <= Math.sqrt(n); i++){
   			if(n%i==0)return false;
  		}
  		return true;
 	}
 	// sieve
 	int[] primes(int n){       // for(int i=1;i<=arr.length-1;i++)out.write(""+arr[i]+" ");
  		boolean arr[] = new boolean[n+1];
  		Arrays.fill(arr,true);
  		arr[1]=false;
  		for(int i=2;i<=Math.sqrt(n);i++){
			if(!arr[i])continue;
			for(int j = 2*i ;j<=n;j+=i){
				arr[j]=false;
			}
  		}
  		LinkedList<Integer> ll = new LinkedList<Integer>();
  		for(int i=1;i<=n;i++){	
   			if(arr[i])ll.add(i);
  		}
  		n = ll.size();
  
  		int primes[] = new int[n+1];
  		for(int i=1;i<=n;i++){
    			primes[i]=ll.removeFirst();
  		}
  		return primes;
 	}
 	long gcd(long a , long b){
  		if(b==0)return a;
  		return gcd(b , a%b);
 	}
 	long lcm(long a , long b){
  		if(a==0||b==0)return 0;
  		return (a*b)/gcd(a,b);
 	}
 	long mulmod(long a , long b ,long mod){
  		if(a==0||b==0)return 0;
  		if(b==1)return a;
   		long ans = mulmod(a,b/2,mod);
   		ans = (ans*2)% mod;
   		if(b%2==1)ans = (a + ans)% mod;
   		return ans;
 	}
 	long pow(long a , long b ,long mod){
   		if(b==0)return 1;
  		if(b==1)return a;
   		long ans = pow(a,b/2,mod);
   		ans = (ans * ans);
   		if(ans >= mod )ans %= mod;
   		
   		if(b%2==1)ans = (a * ans);
   		if(ans >= mod )ans %= mod;
   		
   		return ans;
 	}
 	// 20*20   nCr Pascal Table
 	long[][] ncrTable(){
  		long ncr[][] = new long[21][21];
  		for(int i=0 ;i<=20 ;i++){ncr[i][0]=1;ncr[i][i]=1;}
  		for(int j=0;j<=20 ;j++){
   			for(int i=j+1;i<= 20 ;i++){
    				ncr[i][j] = ncr[i-1][j]+ncr[i-1][j-1];
   			}
  		}
  	return ncr;
 	}
	//*******************************I/O******************************//	
	int i()throws Exception{
 		//return Integer.parseInt(br.readLine().trim());
 		return in.nextInt();
	}
	int[] is(int n)throws Exception{
  //int arr[] = new int[n+1];
  		for(int i=1 ; i <= n ;i++)tempints[i] = in.nextInt();  
 		return tempints;
	}
	long l()throws Exception{
 		return in.nextLong();
	}
	long[] ls(int n)throws Exception{
 		for(int i=1 ; i <= n ;i++)templongs[i] = in.nextLong();  
 		return templongs;
	}

	double d()throws Exception{
 		return in.nextDouble();
	}
	double[] ds(int n)throws Exception{
  		for(int i=1 ; i <= n ;i++)tempdoubles[i] = in.nextDouble();  
 		return tempdoubles;
	}
	char c()throws Exception{
 		return in.nextCharacter();
	}
	char[] cs(int n)throws Exception{
  		for(int i=1 ; i <= n ;i++)tempchars[i] = in.nextCharacter();  
 		return tempchars;
	}
	String s()throws Exception{
 		return in.nextLine();
	}
	BigInteger bi()throws Exception{
 		return in.nextBigInteger();
	}
//***********************I/O ENDS ***********************//
//*********************** 0.3%f [precision]***********************//
/* roundoff upto 2 digits 
   double roundOff = Math.round(a * 100.0) / 100.0;
                    or
   System.out.printf("%.2f", val);
					
*/
/*
  print upto 2 digits after decimal
  val = ((long)(val * 100.0))/100.0;
  
*/    private void closeResources(){
		 out.flush();
    		 out.close();
    		 return;
	}
	public static void main(String[] args) throws java.lang.Exception{
		//let_me_start Shinch Returns 
		
 
    /*  
    	  // Old Reader Writer
    	  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out=new BufferedWriter(new OutputStreamWriter(System.out));
	  BufferedReader br=new BufferedReader(new FileReader("input.txt"));
        BufferedWriter out=new BufferedWriter(new FileWriter("output.txt"));
    */ 	 
    		 A driver = new A(true);
    		 long start =  System.currentTimeMillis();
    		 driver.run();
    		 long end =  System.currentTimeMillis();
    		 //out.write(" Total Time : "+(end - start)+"\n");
    		 driver.closeResources();
    		 return ;
    		 
	}	 

}

class FastReader{

	private boolean finished = false;

	private InputStream stream;
	private byte[] buf = new byte[4*1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public FastReader(InputStream stream){
		this.stream = stream;
	}

	public int read(){
		if (numChars == -1){
			throw new InputMismatchException ();
		}
		if (curChar >= numChars){
			curChar = 0;
			try{
				numChars = stream.read (buf);
			} catch (IOException e){
				throw new InputMismatchException ();
			}
			if (numChars <= 0){
				return -1;
			}
		}
		return buf[curChar++];
	}

	public int peek(){
		if (numChars == -1){
			return -1;
		}
		if (curChar >= numChars){
			curChar = 0;
			try{
				numChars = stream.read (buf);
			} catch (IOException e){
				return -1;
			}
			if (numChars <= 0){
				return -1;
			}
		}
		return buf[curChar];
	}

	public int nextInt(){
		int c = read ();
		while (isSpaceChar (c))
			c = read ();
		int sgn = 1;
		if (c == '-'){
			sgn = -1;
			c = read ();
		}
		int res = 0;
		do{
			if(c==','){
				c = read();
			}
			if (c < '0' || c > '9'){
				throw new InputMismatchException ();
			}
			res *= 10;
			res += c - '0';
			c = read ();
		} while (!isSpaceChar (c));
		return res * sgn;
	}

	public long nextLong(){
		int c = read ();
		while (isSpaceChar (c))
			c = read ();
		int sgn = 1;
		if (c == '-'){
			sgn = -1;
			c = read ();
		}
		long res = 0;
		do{
			if (c < '0' || c > '9'){
				throw new InputMismatchException ();
			}
			res *= 10;
			res += c - '0';
			c = read ();
		} while (!isSpaceChar (c));
		return res * sgn;
	}

	public String nextString(){
		int c = read ();
		while (isSpaceChar (c))
			c = read ();
		StringBuilder res = new StringBuilder ();
		do{
			res.appendCodePoint (c);
			c = read ();
		} while (!isSpaceChar (c));
		return res.toString ();
	}

	public boolean isSpaceChar(int c){
		if (filter != null){
			return filter.isSpaceChar (c);
		}
		return isWhitespace (c);
	}

	public static boolean isWhitespace(int c){
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	private String readLine0(){
		StringBuilder buf = new StringBuilder ();
		int c = read ();
		while (c != '\n' && c != -1){
			if (c != '\r'){
				buf.appendCodePoint (c);
			}
			c = read ();
		}
		return buf.toString ();
	}

	public String nextLine(){
		String s = readLine0 ();
		while (s.trim ().length () == 0)
			s = readLine0 ();
		return s;
	}

	public String nextLine(boolean ignoreEmptyLines){
		if (ignoreEmptyLines){
			return nextLine ();
		}else{
			return readLine0 ();
		}
	}

	public BigInteger nextBigInteger(){
		try{
			return new BigInteger (nextString ());
		} catch (NumberFormatException e){
			throw new InputMismatchException ();
		}
	}

	public char nextCharacter(){
		int c = read ();
		while (isSpaceChar (c))
			c = read ();
		return (char) c;
	}

	public double nextDouble(){
		int c = read ();
		while (isSpaceChar (c))
			c = read ();
		int sgn = 1;
		if (c == '-'){
			sgn = -1;
			c = read ();
		}
		double res = 0;
		while (!isSpaceChar (c) && c != '.'){
			if (c == 'e' || c == 'E'){
				return res * Math.pow (10, nextInt ());
			}
			if (c < '0' || c > '9'){
				throw new InputMismatchException ();
			}
			res *= 10;
			res += c - '0';
			c = read ();
		}
		if (c == '.'){
			c = read ();
			double m = 1;
			while (!isSpaceChar (c)){
				if (c == 'e' || c == 'E'){
					return res * Math.pow (10, nextInt ());
				}
				if (c < '0' || c > '9'){
					throw new InputMismatchException ();
				}
				m /= 10;
				res += (c - '0') * m;
				c = read ();
			}
		}
		return res * sgn;
	}

	public boolean isExhausted(){
		int value;
		while (isSpaceChar (value = peek ()) && value != -1)
			read ();
		return value == -1;
	}

	public String next(){
		return nextString ();
	}

	public SpaceCharFilter getFilter(){
		return filter;
	}

	public void setFilter(SpaceCharFilter filter){
		this.filter = filter;
	}

	public interface SpaceCharFilter{
		public boolean isSpaceChar(int ch);
	}
}
 /******************** Pair class ***********************/
 
 class Pair implements Comparable<Pair>{
 public int a;
 public int b;
 public Pair(){
  this.a = 0;
  this.b = 0;
 }
 public Pair(int a,int b){
  this.a = a;
  this.b = b;
 }
 public int compareTo(Pair p){
  if(this.a==p.a){
   return this.b-p.b;  
  }
  return this.a-p.a; 
 }
 public String toString(){
  return "a="+this.a+" b="+this.b;
 }
 
} 
