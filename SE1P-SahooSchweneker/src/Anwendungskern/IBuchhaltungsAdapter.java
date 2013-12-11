package Anwendungskern;
import Exceptions.ZahlungFehlgeschlagenException;
import FachlicheTypen.ZahlungsinformationsTyp;

public interface IBuchhaltungsAdapter {
	void bucheZahlung(ZahlungsinformationsTyp zahlungsinformation)
			throws ZahlungFehlgeschlagenException;
}
