package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 */

/**
 * �����i�������j�̃N���X�B<br>
 * ��_�x�N�g���ƕ����x�N�g�������B<br>
 * ��_�x�N�g���A�����x�N�g���͑S��final�����B
 * 
 * @see mathLib.Plane
 *
 */
public class Ray extends Line{
	/**
	 * �ˏo�̊�_�x�N�g��
	 */
	protected final Vector pos;
	/**
	 * �ˏo�̕����x�N�g��
	 */
	protected final Vector vel;
	
	/**
	 * ��_�Ǝˏo�����̃x�N�g�����������i�������j�̃R���X�g���N�^
	 * @param pos
	 * �x�N�g���ˏo�̊�_�x�N�g���BLine�N���X�ƈႢ�����x�N�g���ɒ�������Ƃ͌���Ȃ�
	 * @param vel
	 * �����̎ˏo�����x�N�g��
	 * @exception
	 * java.lang.RuntimeException.RuntimeException ��������v���Ȃ��H�������킹�̏ꍇ�ɔ���
	 */
	public Ray(Vector pos, Vector vel) {
		super(pos,vel);
		//Line�N���X���vel�͐��K�������̂ł�����㏑������
		if(pos.m != vel.m){
			throw new RuntimeException("�����̎�������v���܂���");
		}
		this.pos = pos;
		this.vel = vel;
	}
	
	/**
	 * ������n�t���[����ɓ��B������W���̃x�N�g����Ԃ��B
	 * ���̌����͕ύX����Ȃ�
	 * @param time
	 * vel������t���[����
	 * @return
	 * n�t���[����Ɍ��������B������W
	 */
	public Vector arrivalPosition(double time){
		Vector result = pos.add(vel.scalarMultiple(time));
		return result;
	}
	
	/**
	 * ���������ʂɓ��B����܂łɂ�����t���[������Ԃ��B
	 * ���̌����ƕ��ʂ͕ύX����Ȃ��B
	 * �����_�̎����ł͌��������ʂ��痣���ꍇ���l�����Ă��Ȃ�
	 * @param plane
	 * ���B�����������ʂ̃x�N�g��
	 * @return
	 * ���B�܂łɂ�����t���[����
	 */
	public double crossDetectWithPlane(Plane plane){
		double result = 0;
		result = (plane.d - this.pos.dotProduct(plane.normal))/(this.vel.dotProduct(plane.normal));
		return result;
	}	
}