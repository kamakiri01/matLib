package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 * This library manage (m,n)Matrix by double[m][n].
 * An element(m,n) is NOT matching matrix[m][n], its point of matrix[m-1][n-1].
 */

/**
 * ベクトルのクラス。行または列数が1の、条件付き行列クラスの別名。<br>
 * 行数、列数、実体のdouble二次元配列は全てfinal属性。
 * 
 * @see mathLib.Matrix
 *
 */
public class Vector extends Matrix{
	
	//
	/**
	 * 値を指定してベクトルを作るコンストラクタ<br>
	 * 
	 * 列ベクトルの生成<br>
	 * Matrix myCol = new Vector({<br>
	 * 		{1},<br>
	 * 		{0},<br>
	 * 		{0}});<br>
	 * 
	 * 行ベクトルの生成<br>
	 * Matrix myRow = new Vector({<br>
	 * 		{1,0,0}});<br>
	 * @param source
	 * 要素を持った列ベクトル
	 * @exception java.lang.RuntimeException.RuntimeException 行数あるいは列数が1でない場合に発生
	 */
	public Vector(double[][] source){
		super(source);
		if(source.length != 1 && source[0].length != 1){
			throw new RuntimeException("ベクトルを生成できない配列です");
		}
	}
	
	/**
	 * 値を指定して列ベクトルを作るコンストラクタ(一次元配列)<br>
	 * 
	 * 
	 * Matrix mydata = new Vector(3,{<br>
	 * 		3,<br>
	 * 		2,<br>
	 * 		1});<br>
	 *
	 * @param source
	 * ベクトルの実体となる一次元配列
	 */
	public Vector(double[] source){
		super(1, source.length, source);
		this.toCol();
	}
	
	//
	/**
	 * (m,n)型の空ベクトルを作るコンストラクタ。Vector型の整合性のためクラス外では使用されない
	 * @param m
	 * m行のベクトルを生成する
	 * @param n
	 *　n列のベクトルを生成する
	 */
	private Vector(int m, int n){
		super(m,n);
	}
	
	//
	/**
	 * 要素数(m, 1)、要素はすべて0の列ベクトルを作るコンストラクタ
	 * @param m
	 * ベクトルの要素数
	 */
	public Vector(int m){
		super(m, 1);
	}
	
	/**
	 * ベクトルを行ベクトルにして返す。元のベクトルは変更されない
	 * @return
	 * 一行ベクトル
	 */
	public Vector toRow(){
		Matrix result = new Vector(this.value);
		if(result.m != 1){
			result = result.transpose();
		}
		return new Vector(result.value);
	}

	/**
	 * ベクトルを列ベクトルにして返す。元のベクトルは変更されない
	 * @return
	 * 一列ベクトル
	 */
	public Vector toCol(){
		Matrix result = new Vector(this.value);
		if(result.n != 1){
			result = result.transpose();
		}
		return new Vector(result.value);
	}
	
	/**
	 * ベクトルの自己内積の値を返す。ベクトルの大きさに等しい
	 * @return
	 * ベクトルの大きさ
	 */
	public double size(){
		double result = 0;
		result = this.dotProduct(this);
		result = Math.sqrt(result);
		return result;
	}
	
	//ベクトルをスカラー倍して返す
	//縦ベクトル、横ベクトルかは指定しない
	/* (非 Javadoc)
	 * @see Matrix#scalarMultiple(double)
	 */
	/**
	 * ベクトルの各要素をスカラー倍した新しいベクトルを返す。
	 * 元のベクトルは変更されない
	 * 
	 * @param scalar
	 * 倍率
	 * @return
	 * scalar倍したベクトル。
	 * @see Matrix#scalarMultiple(double)
	 */
	public Vector scalarMultiple(double scalar){
		Vector result = new Vector(this.value);
		for(int i = 0;i<result.m;i++){
			for(int j =0;j<result.n;j++){
				result.value[i][j] = result.value[i][j] * scalar;
			}
		}
		return result;
	}
	

	/* (非 Javadoc)
	 * 次元の等しいベクトル2つの要素をそれぞれ足し合わせたベクトルを返す。
	 * 元のベクトルは変更されない
//	 * 縦横の次元が異なる場合は左項に合わせる
	 * @see Matrix#add(Matrix)
	 */
	/**
	 * 次元の等しいベクトル2つの要素をそれぞれ足し合わせたベクトルを返す。
	 * 元のベクトルは変更されない
	 * @param source
	 * 加算するベクトル
	 * @return
	 * 加算されたベクトル
	 * @exception java.lang.RuntimeException.RuntimeException 適切なベクトルの組み合わせでない場合に発生
	 * @see Matrix#add(Matrix)
	 */
	public Vector add(Vector source){
//		
//		if(this.m == 1){
//			//行ベクトルに合わせる
//		}else if(this.n == 1){
//			//列ベクトルに合わせる
//		}
		if(this.m != source.m || this.n != source.n){
			throw new RuntimeException("ベクトルの次元が一致しません");
		}

		int m = this.m;
		int n = this.n;
		Vector result = new Vector(m,n);//this.mirror();
		for(int i = 0;i<m;i++){
			for(int j =0;j<n;j++){		
				result.value[i][j] = this.value[i][j] + source.value[i][j];
			}
		}
		return result;
	}
	/* (非 Javadoc)
	 * 次元の等しいベクトル2つの要素をそれぞれ引いたベクトルを返す。
	 * 元のベクトルは変更されない
//	 * 縦横の次元が異なる場合は左項に合わせる
	 * @see Matrix#sub(Matrix)
	 */
	/**
	 * 次元の等しいベクトル2つの要素をそれぞれ引いたベクトルを返す。
	 * 元のベクトルは変更されない
	 * @param source
	 * 減算する右項のベクトル
	 * @return
	 * 減算されたベクトル
	 * @exception java.lang.RuntimeException.RuntimeException 適切なベクトルの組み合わせでない場合に発生
	 * @see Matrix#sub(Matrix)
	 */
	public Vector sub(Vector source){
//		
//		if(this.m == 1){
//			//行ベクトルに合わせる
//		}else if(this.n == 1){
//			//列ベクトルに合わせる
//		}
		if(this.m != source.m || this.n != source.n){
			throw new RuntimeException("ベクトルの次元が一致しません");
		}

		int m = this.m;
		int n = this.n;
		Vector result = new Vector(m,n);//this.mirror();
		for(int i = 0;i<m;i++){
			for(int j =0;j<n;j++){		
				result.value[i][j] = this.value[i][j] - source.value[i][j];
			}
		}
		return result;
	}	
	/* (非 Javadoc)
	 * ベクトルを単位行列化して返す
	 * @see Matrix#toIdentity()
	 */
	/**
	 * ベクトルを単位行列化して返す。元のベクトルは変更されない
	 * @return
	 * 単位化された行列
	 */
	public Vector toIdentity(){
		Vector result = new Vector(this.value);
		double scale = Math.sqrt(this.dotProduct(this));
		if(scale == 0){
			return this;
		}
		result = result.scalarMultiple(1/scale);
		return result;
	}
	
	public Vector normalize(){
		return this.toIdentity();
	}

	/**
	 * 自身と引数ベクトルの内積の結果を返す。元のベクトルは変更されない
	 * 
	 * @param source
	 * 任意のベクトル。自身と長さが一致していない場合エラーを返す。
	 * 
	 * @return
	 * 内積値
	 * @exception java.lang.RuntimeException.RuntimeException 適切なベクトルの組み合わせでない場合に発生
	 */
	public double dotProduct(Vector source){
		Vector tmp1 = this.toCol();
		Vector tmp2 = source.toCol();
		
		if(tmp1.m != tmp2.m){
			throw new RuntimeException("ベクトル長が一致していません");
		}
		double result = 0;
		for(int i = 0;i<tmp1.m;i++){
			result += tmp1.value[i][0] * tmp2.value[i][0];
		}
		return result;
	}
	/**
	 * 自身と引数ベクトルの内積を正規化した結果を返す。
	 * 元のベクトルは変更されない
	 * 
	 * @param source
	 * 任意のベクトル。自身と長さが一致していない場合エラーを返す。
	 * 
	 * @return
	 * 正規化された内積値
	 * @exception java.lang.RuntimeException.RuntimeException 適切なベクトルの組み合わせでない場合に発生
	 */
	public double dotProductNormalize(Vector source){
//		Vector tmp1 = this.toCol();
//		Vector tmp2 = source.toCol();
//		
//		if(tmp1.m != tmp2.m){
//			throw new RuntimeException("ベクトル長が一致していません");
//		}
//		double result = 0;
//		for(int i = 0;i<tmp1.m;i++){
//			result += tmp1.value[i][0] * tmp2.value[i][0];
//		}
//		result = result/this.size();
//		result = result/source.size();
//		System.out.println("getDotSourceP");
//		Vector.printMatrix(this);
//		Vector.printMatrix(source);
		Vector tmp1 = this.toCol().normalize();
		Vector tmp2 = source.toCol().normalize();
		
//		System.out.println("getDotSource");
//		Vector.printMatrix(tmp1);
//		Vector.printMatrix(tmp2);
		
		if(tmp1.m != tmp2.m){
			throw new RuntimeException("ベクトル長が一致していません");
		}
//		System.out.println("dPN-cl: "+tmp1.m +":"+tmp2.m);
		double result = 0;
		for(int i = 0;i<tmp1.m;i++){
			result += tmp1.value[i][0] * tmp2.value[i][0];
//			System.out.println("dPN-i: "+tmp1.value[i][0] + ":"+tmp2.value[i][0]);
//			System.out.println("dPN: "+result);
		}
//		System.out.println("dPN: "+result);
		return result;
	}
	
	/**
	 * 自身と引数ベクトルの外積の結果を返す。
	 * いずれかが3次元以外のベクトルの場合エラーを返す。元のベクトルは変更されない
	 * @param source
	 * 任意のベクトル。自身と長さが一致していない場合エラーを返す。
	 * 
	 * @return
	 * 外積ベクトル
	 * @exception java.lang.RuntimeException.RuntimeException 適切なベクトルの組み合わせでない場合に発生
	 * @exception java.lang.RuntimeException.RuntimeException 3次元ベクトル以外の場合に発生
	 */
	public Vector crossProduct(Vector source){
		Vector tmp1 = this.toCol();
		Vector tmp2 = source.toCol();
		
		if(tmp1.m != tmp2.m){
			throw new RuntimeException("ベクトル長が一致していません");
		}else if(tmp1.m != 3){
			throw new RuntimeException("3次元以外のベクトルには適用できません");
		}
		
	Vector result = new Vector(tmp1.m);
	result.value[0][0] = tmp1.value[1][0]*tmp2.value[2][0] - tmp1.value[2][0]*tmp2.value[1][0];  
	result.value[1][0] = tmp1.value[2][0]*tmp2.value[0][0] - tmp1.value[0][0]*tmp2.value[2][0];  
	result.value[2][0] = tmp1.value[0][0]*tmp2.value[1][0] - tmp1.value[1][0]*tmp2.value[0][0];  
	return result;
	}
	/**
	 * ベクトルの要素をString型に変換して返す
	 */
	public String toString(){
		return this.value.toString();
	}
}