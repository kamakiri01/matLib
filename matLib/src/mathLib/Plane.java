package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 */

/**
 * ３次元空間上の平面クラス。<br>
 * ４次元の平面ベクトルとこれを分解した法線ベクトル、原点からの距離を持つ。<br>
 * 平面ベクトル、法線ベクトル、原点からの距離は全てfinal属性。
 * 
 * @see mathLib.Line
 * @see mathLib.Ray
 *
 */
public class Plane{
	
	/**
	 * 平面ベクトルの要素の実体（n次元空間の平面ならn個の要素を持つ）
	 */
	protected final double[][] value;
	/**
	 * 平面の法線ベクトル。<br>
	 * valueの前方3要素を切り取った値によるベクトル。<br>
	 * 法線要素は単位長ベクトル化される
	 */
	protected final Vector normal;
	/**
	 * 平面を持つ空間の原点からの距離。<br>
	 * valueの末尾値を切り取った要素。<br>
	 * ベクトルは単位長化される
	 */
	protected final double d;
	
	/**
	 * 法線ベクトルと平面上の一点を持つ平面のコンストラクタ。<br>
	 * 4次元以上の空間における平面表現には対応していない
	 * @param normal
	 * 平面の法線ベクトル
	 * @param anchor
	 * 平面上の任意の一点。法線ベクトルとは異なるベクトルでなくてはならない
	 */
	public Plane (Vector normal, Vector anchor){
		if(normal.m != anchor.m){
			throw new RuntimeException("引数の次元が一致しません");
		}else if(normal.m != 3){
			throw new RuntimeException("このコンストラクタは3次元以外で平面を定義できません");
		}else if(normal.eq(anchor)){
			throw new RuntimeException("法線と平面上の一点は同一では定義できません");
		}
		
		this.normal = normal.toCol().normalize();// test
		this.d = normal.dotProduct(anchor);
		
		this.value = this.normal.value;
		this.value[this.normal.value.length][0] = this.d;
	}
	
	/**
	 * dを１に限定した平面のコンストラクタ。
	 * ax+by+cz=1
	 * @param a
	 * 平面の方程式におけるxの係数
	 * @param b
	 * 平面の方程式におけるyの係数
 	 * @param c
	 * 平面の方程式におけるzの係数
	 * 
	 */
	public Plane (double a, double b, double c){
		
		this(
				new double[][] {{a},{b},{c},{1}}
						);
		
//		double[][] tmp1 = new double[4][1];
//		tmp1[0][0] = a;
//		tmp1[1][0] = b;
//		tmp1[2][0] = c;
//		tmp1[3][0] = 1;
//		this(tmp1);
		
//		double[][] tmp2 = new double[3][1];
//		tmp2[0][0] = a;
//		tmp2[1][0] = b;
//		tmp2[2][0] = c;
//		this.normal = new Vector(tmp2);
//		this.d = 1;
	}
	
	/**
	 * 平面ベクトルの2次元配列を与えて平面を作るコンストラクタ
	 * 
	 * @param source
	 * 平面ベクトルの実体となる二次元配列
	 * 
	 */
	public Plane (double[][] source){
		if(source.length != 4){
			throw new RuntimeException("このコンストラクタは3次元以外で平面を定義できません");
		}
		double[][] tmp1 = new double[3][1];
		tmp1[0][0] = source[0][0];
		tmp1[1][0] = source[1][0];
		tmp1[2][0] = source[2][0];
		
		Vector tmp1v = new Vector(tmp1);
		
		double[][] tmp2 = tmp1v.normalize().value; //test
		
		this.value = new double[4][1];
		this.value[0][0] = tmp2[0][0];
		this.value[1][0] = tmp2[1][0];
		this.value[2][0] = tmp2[2][0];
		this.value[3][0] = source[3][0];
//		
//		double[][] tmp = new double[3][1];
//		tmp[0][0] = source[0][0];
//		tmp[1][0] = source[1][0];
//		tmp[2][0] = source[2][0];
		
		this.normal = tmp1v;//new Vector(tmp);
		this.d = source[source.length-1][0];
	}
	
	/**
	 * 平面ベクトルを与えて平面を作るコンストラクタ
	 * 
	 * @param source
	 * 平面ベクトル
	 * 
	 */
	public Plane (Vector source){
		this(source.value);
	}
	
	/**
	 * 平面を与えてコピー平面を作るコンストラクタ(ディープコピー)
	 * 
	 * @param source
	 * コピー元平面
	 * 
	 */
	public Plane (Plane source){
		this(source.value);
	}
	
	/**
	 * 空間上の任意の点から平面までの距離を返す
	 * @param dot
	 * 空間上の任意の点ベクトル
	 * @return
	 * 距離値
	 */
	public double distanceFromDot(Vector dot){
		if(this.normal.m != dot.m){
			throw new RuntimeException("引数の次元が一致しません");
		}
		double result = this.normal.dotProduct(dot) / this.normal.size();
		return result;
	}
	/**
	 * 平面ベクトルの要素をString型に変換して返す
	 */
	public void printPlane(){
		Vector tmp = new Vector(this.value);
		tmp.printMatrix();
	}
	
	/**
	 * 平面ベクトルの要素をString型に変換して返す
	 */
	public String toString(){
		return this.value.toString();
	}
}