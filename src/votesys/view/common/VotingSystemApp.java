package votesys.view.common;

import votesys.view.admin.AdminMain;
import votesys.view.user.UserMain;

public class VotingSystemApp {
	
	public static void main(String[] args) {
		new UserMain();	// User 시스템
		new AdminMain();	// Admin 시스템
	}
	
}
