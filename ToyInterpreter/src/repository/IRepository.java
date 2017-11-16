package repository;
import java.io.IOException;
import model.PrgState;

public interface IRepository {
	public PrgState getCrtState();
	void logPrgStateExec() throws IOException;
}
