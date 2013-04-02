package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 */

/**
 * 直線のクラス。<br>
 * 基点ベクトルと方向ベクトルを持つ。<br>
 * 基点ベクトル、方向ベクトルは全てfinal属性。
 * 
 * @see mathLib.Plane
 * @see mathLib.Ray
 * 
 *
 */
public class Line{
	/**
	 * 射出の基点ベクトル
	 */
	protected final Vector pos;
	/**
	 * 射出の方向ベクトル
	 */
	protected final Vector vel;
	
	/**
	 * 基点と射出方向のベクトルを持つ直線のコンストラクタ
	 * @param pos
	 * ベクトル射出の基点ベクトル。直線に直交する
	 * @param vel
	 * 射出方向ベクトル
	 * 
	 * @see mathLib.Ray
	 */
	public Line(Vector pos, Vector vel){
		if(pos.m != vel.m){
			throw new RuntimeException("引数の次元が一致しません");
		}
		//位置ベクトルと方向ベクトルが直交していない場合にグラム・シュミット法で直交化する
		double thetaDeg = Math.toDegrees(Math.asin(pos.dotProductNormalize(vel)));
		//浮動小数点で3桁以下は発生しうる誤差と看做しています
		if(thetaDeg >  0.0005 ){//|| thetaDeg < 364.9995){ 
			Vector crossPos = new Vector(pos.m);
			crossPos = pos.add(
					vel.scalarMultiple(
							pos.dotProduct(vel) / vel.dotProduct(vel) *(-1)
							));
			
			this.pos = crossPos.toCol();
			this.vel = vel.toCol();
//			System.out.println("引数は直交していません>角度: "+thetaDeg);	
		}else{
			this.pos = pos.toCol();
			this.vel = vel.toCol();	
//			System.out.println("引数は直交しています");
		}
	}
	
	/**
	 * 得たい直線が通る2点を指定して直線を生成するコンストラクタ
	 * 
	 * @param vec1
	 * 直線が通る任意の1点
	 * @param vec2
	 * 直線がとおり、引数1とは異なる1点
	 * @return
	 * 2点を通る直線オブジェクト
	 */
	public static Line createLineBy2Points(Vector vec1, Vector vec2){
		if(vec1.m != vec2.m){
			throw new RuntimeException("引数の次元が一致しません");
		}
		Vector vel = new Vector(
				vec1.add(
						vec2.scalarMultiple(-1)).value);
		return new Line(vec1, vel);
	}
	
	/**
	 * 直線の射出の基点ベクトルを返す
	 * @return
	 * 基点ベクトル
	 */
	public Vector getPos(){
		return this.pos;
	}
	
	/**
	 * 直線の射出の方向ベクトルを返す
	 * @return
	 * 方向ベクトル
	 */
	public Vector getVel(){
		return this.vel;
	}
	
	
	//未完成なので手法を変えると思われる
	//@see http://mathworld.wolfram.com/Line-LineDistance.html
	/**
	 * 2つの直線が最接近したときの距離を返す
	 * 直線ベクトルは変更されない
	 * @param line1
	 * 交差を判定する直線１
	 * @param line2
	 * 交差を判定する直線１
	 * @return
	 * 接近時の距離値
	 */
	public static double crossDetect(Line line1, Line line2){
//		double result;
//		result = Math.abs(
//				(line1.getPos().add(line2.getPos().scalarMultiple(-1)))
//					.dotProduct(line1.getVel().crossProduct(line2.getVel())));
//		result = result/ 
//					(line1.getVel().crossProduct(line2.getVel())).size();
//		return result;
		Vector normal = new Vector(
				line1.vel.crossProduct(line2.vel)
					.value);
		Vector v21 = line1.pos.add(
				line2.pos.scalarMultiple(-1));
		double result = normal.dotProduct(v21) / normal.size();
		return result;
			
	}
	
	/**
	 * LineのメンバーをSystem.out.printlnする
	 */
	public void printLine(){
		System.out.println("pos(baseVector): ");
		pos.printMatrix();
		System.out.println("vel(directionVector): ");
		vel.printMatrix();

	}
	
	//メンバー要素を配列化して取得する
	public Vector[] getValue(){
		Vector[] hoge =new Vector[2];
		hoge[0] = this.pos;
		hoge[1] = this.vel;
		return hoge;
//		System.out.println("pos(baseVector): ");
//		pos.printMatrix();
//		System.out.println("vel(directionVector): ");
//		vel.printMatrix();

	}
	
}