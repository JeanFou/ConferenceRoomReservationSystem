package register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import master.ClientMasterController;
import transmission.LoginData;
import transmission.TransmissionData;

public class RegisterController implements ActionListener {

	private RegisterModel rm;
	private RegisterView rv;
	private TransmissionData data;

	public RegisterController(RegisterModel m, RegisterView v) {
		rm = m;
		rv = v;
		rv.setLoginListener(this);
	}

	public boolean controlModel(TransmissionData data) {
		if (data.getFlags() == 7) {
			// there is same named room
			rm.setMessage(data.getMessage());
			return true;
		} else if (data.getFlags() == 6) {
			// successfully registered
			rm.setMessage(data.getMessage());
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("취소")) {
			data = new TransmissionData();
			data.setFlags(2);
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		rm.setId(rv.getIdStr());
		rm.setPassword(rv.getPwStr());
		rm.setConfirmPassword(rv.getPwConfimStr());
		rm.setContact(rv.getContactStr());
		rm.setEmail(rv.getEmailStr());
		rm.setName(rv.getNameStr());
		
		if (!rm.getPassword().equals(rm.getConfirmPassword())) {
			rm.setMessage("비밀번호가 서로 같지 않습니다.");
			return;
		}
		
		if (rv.isEpu() && rv.isNmu()) {
			rm.setMessage("사용자 하나만을 선택하세요.");
		} else if (rv.isEpu() == false && rv.isNmu() == false) {
			rm.setMessage("사용자를 선택하세요.");
		} else if (rv.isEpu()) {
			
		} else if (rv.isNmu()) {
			
		}
	}

}