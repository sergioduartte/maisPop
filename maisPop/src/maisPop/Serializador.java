package maisPop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializador {
	
	public void fechaSistema(Controller controlador) throws Exception{
		if (controlador.getUsuarioLogado() != null) {
			throw new Exception("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
		ObjectOutputStream objectOut = null;
		try {
			objectOut = new ObjectOutputStream(new FileOutputStream("./arquivos/maisPop.ser"));
			objectOut.writeObject(controlador);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			objectOut.close();
		}
	}
	
	public Controller iniciaSistema() {
		
		ObjectInputStream objectIn = null;
		
		try {
			File file = new File("./arquivos/maisPop.ser");
			FileInputStream fileIn = new FileInputStream(file);
			objectIn = new ObjectInputStream(fileIn);
			Controller o1 = (Controller) objectIn.readObject();
			objectIn.close();
			file.delete();
			return o1;
			
		} catch (FileNotFoundException e) {
			return null;
		} catch (Exception e1){
			e1.printStackTrace();
		}
		return null;
	}
	
}
