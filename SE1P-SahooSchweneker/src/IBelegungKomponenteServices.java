import java.util.List;

import Exceptions.FehlgeschlageneReservierungException;
import Exceptions.UnerlaubteZimmerauswahlException;
import FachlicheTypen.AnforderungslisteTyp;
import FachlicheTypen.BooleanTyp;
import FachlicheTypen.ReservierungsNrTyp;
import FachlicheTypen.ZahlungsinformationsTyp;
import FachlicheTypen.ZimmerNrTyp;

public interface IBelegungKomponenteServices {
	
	BooleanTyp gibtFreieAnforderungserfüllendeZimmer(
			AnforderungslisteTyp anforderungsliste);
	
	List<ZimmerNrTyp> holeErfüllendeZimmerHoherPreisklasse(
			AnforderungslisteTyp anforderungsliste)
			throws UnerlaubteZimmerauswahlException;
	
	BooleanTyp validiereZahlunginformation(
			ZahlungsinformationsTyp zahlungsinformation);
	
	ReservierungsNrTyp reserviereZimmerMitAnforderungen(
			AnforderungslisteTyp anforderungsliste)
			throws FehlgeschlageneReservierungException;
	
	ReservierungsNrTyp reserviereAusgewähltesZimmer(ZimmerNrTyp zimmerNr)
			throws FehlgeschlageneReservierungException;
	
}
