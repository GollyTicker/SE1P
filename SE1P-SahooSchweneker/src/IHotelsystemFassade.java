import java.util.List;

import Exceptions.FehlgeschlageneReservierungException;
import Exceptions.UnerlaubteZimmerauswahlException;
import FachlicheTypen.AnforderungslisteTyp;
import FachlicheTypen.BooleanTyp;
import FachlicheTypen.PreisTyp;
import FachlicheTypen.ReservierungsNrTyp;
import FachlicheTypen.ZahlungsinformationsTyp;
import FachlicheTypen.ZimmerNrTyp;

public interface IHotelsystemFassade {

	// Eine Anforderung kann entweder eine Personenanforderung(Anzahl der
	// Personen, Kinder, Behinderungen),
	// eine Buchungszeitraumanforderung oder eine Preisklassenanforderung sein.

	// Die Operationen für den "Zimmer reservieren Use-Case" werden in der
	// folgenden Reihenfolge durchgeführt:
	// 1. IBooleanTyp
	// gibtFreieAnforderungserfüllendeZimmer(IAnforderungslisteTyp
	// anforderungsListe);
	// 1.b (optional) List<IZimmerNr>
	// holeErfüllendeZimmerHoherPreisklasse(IAnforderungslisteTyp
	// anforderungsListe) throws UnerlaubteZimmerauswahlException;
	// 2. IPreisTyp berechnePreis(IZimmerNr zimmerNr);
	// 3. IBooleanTyp validiereZahlunginformationen(IZahlungsinformationsTyp
	// zahlungsinformation);
	// 4.a1 IReservierungsNr
	// reserviereZimmerMitAnforderungen(IAnforderungslisteTyp anforderungsListe)
	// throws FehlgeschlageneReservierungException;
	// 4.a2 IReservierungsNr reserviereAusgewähltesZimmer(IZimmerNr zimmerNr)
	// throws FehlgeschlageneReservierungException;
	// ( 4.a1 und 4.a2 sind einander ausschließend. )

	/**
	 * (Abfrage) Zu einer Liste von Anforderungen gibt diese Operation zurück,
	 * ob es freie zimmer gibt die diese Anforderungen erfüllen. Falls es diese
	 * gibt, kommt ein wahrer Wert zurück.
	 * 
	 * @param anforderungsliste
	 *            Liste der Anforderungen
	 * @return IBooleanTyp
	 */
	BooleanTyp gibtFreieAnforderungserfüllendeZimmer(
			AnforderungslisteTyp anforderungsliste);

	/**
	 * (Abfrage) Diese Operation gibt für Anforderungen der hohen Preisklasse
	 * eine Liste der freien Zimmer zurück, die diese Anforderungen erfüllen.
	 * Enhalten die Anforderungen nicht die hohe Preisklasse, dann wird eine
	 * UnerlaubteZimmerauswahlException geworfen.
	 * 
	 * @param anforderungsliste
	 *            Liste der Anforderungen
	 * @return List<IZimmerNr>
	 * @throws UnerlaubteZimmerauswahlException
	 */
	List<ZimmerNrTyp> holeErfüllendeZimmerHoherPreisklasse(
			AnforderungslisteTyp anforderungsliste)
			throws UnerlaubteZimmerauswahlException;

	/**
	 * (Abfrage) Diese Operation berechnet zu einem Zimmer dessen aktuellen
	 * Preis
	 * 
	 * @param zimmerNr
	 *            ZimmerNr dessen Preis zu berechnen ist
	 * @return IPreisTyp Preis des Zimmers
	 */
	PreisTyp berechnePreis(ZimmerNrTyp zimmerNr);

	/**
	 * (Abfrage) Die Zahlungsinformationen werden validiert. Bei Erfolg kommt
	 * ein wahrer Wert zurück.
	 * 
	 * @param zahlungsinformation
	 *            Bitcoin/Kreditkarte sowie begleitende Information
	 * @return IBooleanTyp
	 */
	BooleanTyp validiereZahlunginformation(
			ZahlungsinformationsTyp zahlungsinformation);

	/**
	 * (Kommando) Diese Operation wählt für den Gast (mittlere/niedriger
	 * Preisklasse) ein Zimmer mit den gegebenen Anforderungen aus. Bei Erfolg
	 * wird ein Identifikator der Reservierung zurückgegeben. Sonst wird eine
	 * FehlgeschlageneReservierungException geworfen. (@pre) Die Anforderungen
	 * beziehen sich auf die hohe Preisklasse.
	 * 
	 * @param anforderungsliste
	 *            Liste der Anforderungen
	 * @return IReservierungsNr
	 * @throws FehlgeschlageneReservierungException
	 */
	ReservierungsNrTyp reserviereZimmerMitAnforderungen(
			AnforderungslisteTyp anforderungsliste)
			throws FehlgeschlageneReservierungException;

	/**
	 * (Kommando) In der hohen Preisklasse wird diese Operation das vom Gast
	 * gewählte Zimmer reservieren. Bei Erfolg wird ein Identifikator der
	 * Reservierung zurückgegeben. Sonst wird eine
	 * FehlgeschlageneReservierungException geworfen.
	 * 
	 * @param zimmerNr
	 *            Die ZimmerNr des zu reservierenden Zimmers
	 * @return IReservierungsNr
	 * @throws FehlgeschlageneReservierungException
	 */
	ReservierungsNrTyp reserviereAusgewähltesZimmer(ZimmerNrTyp zimmerNr)
			throws FehlgeschlageneReservierungException;
}
