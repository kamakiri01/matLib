package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 */

/**
 * 光線（半直線）のクラス。<br>
 * 基点ベクトルと方向ベクトルを持つ。<br>
 * 基点ベクトル、方向ベクトルは全てfinal属性。
 * 
 * @see mathLib.Plane
 *
 */
public class Ray extends Line{
	/**
	 * 射出の基点ベクトル
	 */
	protected final Vector pos;
	/**
	 * 射出の方向ベクトル
	 */
	protected final Vector vel;
	
	/**
	 * 基点と射出方向のベクトルを持つ光線（半直線）のコンストラクタ
	 * @param pos
	 * ベクトル射出の基点ベクトル。Lineクラスと違い光線ベクトルに直交するとは限らない
	 * @param vel
	 * 光線の射出方向ベクトル
	 * @exception
	 * java.lang.RuntimeException.RuntimeException 次元が一致しない食う見合わせの場合に発生
	 */
	public Ray(Vector pos, Vector vel) {
		super(pos,vel);
		//Lineクラス上でvelは正規化されるのでこれを上書きする
		if(pos.m != vel.m){
			throw new RuntimeException("引数の次元が一致しません");
		}
		this.pos = pos;
		this.vel = vel;
	}
	
	/**
	 * 光線がnフレーム後に到達する座標をのベクトルを返す。
	 * 元の光線は変更されない
	 * @param time
	 * velが走るフレーム数
	 * @return
	 * nフレーム後に光線が到達する座標
	 */
	public Vector arrivalPosition(double time){
		Vector result = pos.add(vel.scalarMultiple(time));
		return result;
	}
	
	/**
	 * 光線が平面に到達するまでにかかるフレーム数を返す。
	 * 元の光線と平面は変更されない。
	 * 現時点の実装では光線が平面から離れる場合を考慮していない
	 * @param plane
	 * 到達させたい平面のベクトル
	 * @return
	 * 到達までにかかるフレーム数
	 */
	public double crossDetectWithPlane(Plane plane){
		double result = 0;
		result = (plane.d - this.pos.dotProduct(plane.normal))/(this.vel.dotProduct(plane.normal));
		return result;
	}	
}