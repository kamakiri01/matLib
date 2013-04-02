package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 */

/**
 * �����̃N���X�B<br>
 * ��_�x�N�g���ƕ����x�N�g�������B<br>
 * ��_�x�N�g���A�����x�N�g���͑S��final�����B
 * 
 * @see mathLib.Plane
 * @see mathLib.Ray
 * 
 *
 */
public class Line{
	/**
	 * �ˏo�̊�_�x�N�g��
	 */
	protected final Vector pos;
	/**
	 * �ˏo�̕����x�N�g��
	 */
	protected final Vector vel;
	
	/**
	 * ��_�Ǝˏo�����̃x�N�g�����������̃R���X�g���N�^
	 * @param pos
	 * �x�N�g���ˏo�̊�_�x�N�g���B�����ɒ�������
	 * @param vel
	 * �ˏo�����x�N�g��
	 * 
	 * @see mathLib.Ray
	 */
	public Line(Vector pos, Vector vel){
		if(pos.m != vel.m){
			throw new RuntimeException("�����̎�������v���܂���");
		}
		//�ʒu�x�N�g���ƕ����x�N�g�����������Ă��Ȃ��ꍇ�ɃO�����E�V���~�b�g�@�Œ���������
		double thetaDeg = Math.toDegrees(Math.asin(pos.dotProductNormalize(vel)));
		//���������_��3���ȉ��͔���������덷�ƊŘ􂵂Ă��܂�
		if(thetaDeg >  0.0005 ){//|| thetaDeg < 364.9995){ 
			Vector crossPos = new Vector(pos.m);
			crossPos = pos.add(
					vel.scalarMultiple(
							pos.dotProduct(vel) / vel.dotProduct(vel) *(-1)
							));
			
			this.pos = crossPos.toCol();
			this.vel = vel.toCol();
//			System.out.println("�����͒������Ă��܂���>�p�x: "+thetaDeg);	
		}else{
			this.pos = pos.toCol();
			this.vel = vel.toCol();	
//			System.out.println("�����͒������Ă��܂�");
		}
	}
	
	/**
	 * �������������ʂ�2�_���w�肵�Ē����𐶐�����R���X�g���N�^
	 * 
	 * @param vec1
	 * �������ʂ�C�ӂ�1�_
	 * @param vec2
	 * �������Ƃ���A����1�Ƃ͈قȂ�1�_
	 * @return
	 * 2�_��ʂ钼���I�u�W�F�N�g
	 */
	public static Line createLineBy2Points(Vector vec1, Vector vec2){
		if(vec1.m != vec2.m){
			throw new RuntimeException("�����̎�������v���܂���");
		}
		Vector vel = new Vector(
				vec1.add(
						vec2.scalarMultiple(-1)).value);
		return new Line(vec1, vel);
	}
	
	/**
	 * �����̎ˏo�̊�_�x�N�g����Ԃ�
	 * @return
	 * ��_�x�N�g��
	 */
	public Vector getPos(){
		return this.pos;
	}
	
	/**
	 * �����̎ˏo�̕����x�N�g����Ԃ�
	 * @return
	 * �����x�N�g��
	 */
	public Vector getVel(){
		return this.vel;
	}
	
	
	//�������Ȃ̂Ŏ�@��ς���Ǝv����
	//@see http://mathworld.wolfram.com/Line-LineDistance.html
	/**
	 * 2�̒������Őڋ߂����Ƃ��̋�����Ԃ�
	 * �����x�N�g���͕ύX����Ȃ�
	 * @param line1
	 * �����𔻒肷�钼���P
	 * @param line2
	 * �����𔻒肷�钼���P
	 * @return
	 * �ڋߎ��̋����l
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
	 * Line�̃����o�[��System.out.println����
	 */
	public void printLine(){
		System.out.println("pos(baseVector): ");
		pos.printMatrix();
		System.out.println("vel(directionVector): ");
		vel.printMatrix();

	}
	
	//�����o�[�v�f��z�񉻂��Ď擾����
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