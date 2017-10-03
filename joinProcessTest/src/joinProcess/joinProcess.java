package joinProcess;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import join.DAO.joinDAO;

public class joinProcess {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
		joinDAO jDAO = new joinDAO();
		jDAO.joinConsol();
	}

}
