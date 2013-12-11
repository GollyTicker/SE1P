package Anwendungskern;
import java.util.List;

import Exceptions.FehlgeschlageneReservierungException;
import Exceptions.GastNichtGefundenException;
import Exceptions.GastRegistrierungFehlgeschlagenException;
import Exceptions.UnerlaubteZimmerauswahlException;
import Exceptions.Ung�ltigeReservierendeG�steException;
import Exceptions.ZahlungFehlgeschlagenException;
import FachlicheTypen.AnforderungslisteTyp;
import FachlicheTypen.BooleanTyp;
import FachlicheTypen.GastNrTyp;
import FachlicheTypen.NameTyp;
import FachlicheTypen.PreisTyp;
import FachlicheTypen.ReserveriendeG�steNrTyp;
import FachlicheTypen.ReservierungsNrTyp;
import FachlicheTypen.ZahlungsinformationsTyp;
import FachlicheTypen.ZimmerNrListeTyp;
import FachlicheTypen.ZimmerNrTyp;

public interface IHotelsystemFassade {

	// Eine Anforderung kann entweder eine Personenanforderung(Anzahl der
	// Personen, Kinder, Behinderungen),
	// eine Buchungszeitraumanforderung oder eine Preisklassenanforderung sein.

	// Die Operationen f�r den "Zimmer reservieren Use-Case" werden in der
	// folgenden Reihenfolge durchgef�hrt:
	// 1. IBooleanTyp
	// gibtFreieAnforderungserf�llendeZimmer(IAnforderungslisteTyp
	// anforderungsListe);
	// 1.b (optional) List<IZimmerNr>
	// holeErf�llendeZimmerHoherPreisklasse(IAnforderungslisteTyp
	// anforderungsListe) throws UnerlaubteZimmerauswahlException;
	// 2. IPreisTyp berechnePreis(IZimmerNr zimmerNr);
	// 3. IBooleanTyp validiereZahlunginformationen(IZahlungsinformationsTyp
	// zahlungsinformation);
	// 4.a1 IReservierungsNr
	// reserviereZimmerMitAnforderungen(IAnforderungslisteTyp anforderungsListe)
	// throws FehlgeschlageneReservierungException;
	// 4.a2 IReservierungsNr reserviereAusgew�hltesZimmer(IZimmerNr zimmerNr)
	// throws FehlgeschlageneReservierungException;
	// ( 4.a1 und 4.a2 sind einander ausschlie�end. )

	/**
	 * (Abfrage) Zu einer Liste von Anforderungen gibt diese Operation zur�ck,
	 * ob es freie zimmer gibt die diese Anforderungen erf�llen. Falls es diese
	 * gibt, kommt ein wahrer Wert zur�ck.
	 * 
	 * @param anforderungsliste
	 *            Liste der Anforderungen
	 * @return IBooleanTyp
	 */
	BooleanTyp gibtFreieAnforderungserf�llendeZimmer(
			AnforderungslisteTyp anforderungsliste);

	/**
	 * (Abfrage) Diese Operation gibt f�r Anforderungen der hohen Preisklasse
	 * eine Liste der freien Zimmer zur�ck, die diese Anforderungen erf�llen.
	 * Enhalten die Anforderungen nicht die hohe Preisklasse, dann wird eine
	 * UnerlaubteZimmerauswahlException geworfen.
	 * 
	 * @param anforderungsliste
	 *            Liste der Anforderungen
	 * @return List<IZimmerNr>
	 * @throws UnerlaubteZimmerauswahlException
	 */
	ZimmerNrListeTyp holeErf�llendeZimmerHoherPreisklasse(
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
	 * (Kommando) Registriert einen neuen Gast mit den gegebenen Informationen.
	 * Diese Operation kann fehlschlagen, falls bereits ein Gast mit dem
	 * gleichen Namen bereits vorhanden ist.
	 * 
	 * @param vorname
	 *            Der Vorname des Gastes
	 * @param nachname
	 *            Der Nachname des Gastes
	 * @param isErwachsen
	 *            Ist der Gast ein Erwachsener?
	 * @param hatBehinderung
	 *            Hat der Gast eine Behinderung?
	 * @return Die GastNr der neue registrierten Gastes
	 * @throws GastRegistrierungFehlgeschlagenException
	 */
	GastNrTyp registriereGast(NameTyp vorname, NameTyp nachname,
			BooleanTyp isErwachsen, BooleanTyp hatBehinderung)
			throws GastRegistrierungFehlgeschlagenException;

	/**
	 * (Abfrage) F�r einen bereits registrierten Gast, kriegt man mit dieser
	 * Operation dessen GastNr zur�ck.
	 * 
	 * @param vorname
	 *            Der Vorname des Gastes
	 * @param nachname
	 *            Der Nachname des Gastes
	 * @param isErwachsen
	 *            Ist der Gast ein Erwachsener?
	 * @param hatBehinderung
	 *            Hat der Gast eine Behinderung?
	 * @return Die GastNr der vorhandenen Gastes
	 * @throws GastNichtGefundenException
	 */
	GastNrTyp holeGast(NameTyp vorname, NameTyp nachname,
			BooleanTyp isErwachsen, BooleanTyp hatBehinderung)
			throws GastNichtGefundenException;

	/**
	 * (Abfrage) Pr�ft ob die zu reservierende G�ste existieren und stellt
	 * sicher, dass die Liste eine g�ltige Menge an reservierenden G�sten
	 * darstellt.
	 * 
	 * @param reservierendeG�ste
	 *            Eine primitive Liste der reservierenden G�sten.
	 * @return Eine fachliche Liste der reservierenden G�sten. Diese Liste ist
	 *         f�r die Reservierungsoperation notwendig.
	 * @throws Ung�ltigeReservierendeG�steException
	 *             Diese Exception f�gt die F�lle f�r ung�ltige G�stelisten oder
	 *             f�r unregistrierte G�ste ab.
	 */
	ReserveriendeG�steNrTyp validiereG�ste(List<GastNrTyp> reservierendeG�ste)
			throws Ung�ltigeReservierendeG�steException;

	/**
	 * (Abfrage) Die Zahlungsinformationen werden validiert. Bei Erfolg kommt
	 * ein wahrer Wert zur�ck.
	 * 
	 * @param zahlungsinformation
	 *            Bitcoin/Kreditkarte sowie begleitende Information
	 * @return IBooleanTyp
	 */
	BooleanTyp validiereZahlunginformation(
			ZahlungsinformationsTyp zahlungsinformation);

	/**
	 * (Kommando) Delegiert die Transaktion an das Buchungsnachbarsystem. Das
	 * Nachbarsystem �bernimmt eine weitere Validierung vor der Pr�fung.
	 * 
	 * @param zahlungsinformation
	 *            Bitcoin/Kreditkarte sowie begleitende Information
	 * @throws ZahlungFehlgeschlagenException
	 *             Wird geworfen falls die Buchung kritisch fehlschl�gt. Die
	 *             Exception enth�lt dann weitere Information zur Transaktion.
	 */
	void bucheZahlung(ZahlungsinformationsTyp zahlungsinformation)
			throws ZahlungFehlgeschlagenException;

	/**
	 * (Kommando) Diese Operation w�hlt f�r den Gast (mittlere/niedriger
	 * Preisklasse) ein Zimmer mit den gegebenen Anforderungen aus. Bei Erfolg
	 * wird ein Identifikator der Reservierung zur�ckgegeben. Sonst wird eine
	 * FehlgeschlageneReservierungException geworfen. (@pre) Die Anforderungen
	 * beziehen sich auf die hohe Preisklasse.
	 * 
	 * @param zahlungsinformation
	 *            Die Information die das Buchhaltungsnachbarsystem f�r die
	 *            Transaktion ben�tigt.
	 * @param reservierendePersonen
	 *            Eine Liste der G�ste die zu dieser Reservierung geh�ren.
	 * @param anforderungsliste
	 *            Liste der Anforderungen
	 * @return IReservierungsNr
	 * @throws FehlgeschlageneReservierungException
	 */
	ReservierungsNrTyp reserviereZimmerMitAnforderungen(
			ZahlungsinformationsTyp zahlungsinformation,
			ReserveriendeG�steNrTyp reservierendePersonen,
			AnforderungslisteTyp anforderungsliste)
			throws FehlgeschlageneReservierungException;

	/**
	 * (Kommando) In der hohen Preisklasse wird diese Operation das vom Gast
	 * gew�hlte Zimmer reservieren. Bei Erfolg wird ein Identifikator der
	 * Reservierung zur�ckgegeben. Sonst wird eine
	 * FehlgeschlageneReservierungException geworfen.
	 * 
	 * @param zahlungsinformation
	 *            Die Information die das Buchhaltungsnachbarsystem f�r die
	 *            Transaktion ben�tigt.
	 * @param reservierendePersonen
	 *            Eine Liste der G�ste die zu dieser Reservierung geh�ren.
	 * @param zimmerNr
	 *            Die ZimmerNr des zu reservierenden Zimmers
	 * @return IReservierungsNr
	 * @throws FehlgeschlageneReservierungException
	 */
	ReservierungsNrTyp reserviereAusgew�hltesZimmer(
			ZahlungsinformationsTyp zahlungsinformation,
			ReserveriendeG�steNrTyp reservierendePersonen, ZimmerNrTyp zimmerNr)
			throws FehlgeschlageneReservierungException;
}

// TODO: zahlugnsinformation im javadoc f�r die lezten beiden hinzuf�gen
// reservierende personen �berall ins javadoc hinzuf�gen
// validiere und buche Zahlung hinzuf�gen dokumentieren udn zuordnen
// ebenso validiere G�ste
