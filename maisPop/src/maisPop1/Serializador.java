package maisPop1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializador {
	
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private final String FILE = "./arquivos/maisPop.txt";

	public void fechaSistema(Controller controlador) throws Exception {
		
		if (controlador.getUsuarioLogado() != null) {
			throw new Exception("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
		
		try {
			objectOut = new ObjectOutputStream(new FileOutputStream(new File(FILE)));
			objectOut.writeObject(controlador);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				objectOut.close();
			} catch (IOException e2) {
				throw new Exception("Sistem nao pode ser fechado. " + e2.getMessage());
			}
		}

	}

	public Controller iniciaSistema(Controller controlador) {

		try {
			objectIn = new ObjectInputStream(new FileInputStream(FILE));
			return (Controller) objectIn.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				objectIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	
}
