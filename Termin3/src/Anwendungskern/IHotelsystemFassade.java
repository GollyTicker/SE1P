package Anwendungskern;

import java.util.List;

import Exceptions.FehlgeschlageneReservierungException;
import Exceptions.GastNichtGefundenException;
import Exceptions.GastRegistrierungFehlgeschlagenException;
import Exceptions.TechnischerFehlerException;
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
	/*
	 * Die Operationen f�r den "Zimmer reservieren Use-Case" werden in der
	 * folgenden Reihenfolge durchgef�hrt:
	 * 
	 * 1.a BooleanTyp gibtFreieAnforderungserf�llendeZimmer(
	 * AnforderungslisteTyp anforderungsliste) throws
	 * TechnischerFehlerException;
	 * 
	 * 1.b (optional) ZimmerNrListeTyp holeErf�llendeZimmerHoherPreisklasse(
	 * AnforderungslisteTyp anforderungsliste) throws
	 * UnerlaubteZimmerauswahlException, TechnischerFehlerException;
	 * 
	 * 2. PreisTyp berechnePreis(ZimmerNrTyp zimmerNr) throws
	 * TechnischerFehlerException;
	 * 
	 * 3. BooleanTyp validiereZahlunginformation( ZahlungsinformationsTyp
	 * zahlungsinformation) throws TechnischerFehlerException;
	 * 
	 * 4.a1 ReservierungsNrTyp reserviereZimmerMitAnforderungen(
	 * ZahlungsinformationsTyp zahlungsinformation, ReserveriendeG�steNrTyp
	 * reservierendePersonen, AnforderungslisteTyp anforderungsliste) throws
	 * FehlgeschlageneReservierungException, TechnischerFehlerException;
	 * 
	 * 4.a2 ReservierungsNrTyp reserviereAusgew�hltesZimmer(
	 * ZahlungsinformationsTyp zahlungsinformation, ReserveriendeG�steNrTyp
	 * reservierendePersonen, ZimmerNrTyp zimmerNr) throws
	 * FehlgeschlageneReservierungException, TechnischerFehlerException;
	 * 
	 * ( 4.a1 und 4.a2 sind einander ausschlie�end. )
	 */
	// Mehr Informationen finden sie im beigef�gten README.pdf

	/**
	 * (Abfrage) Zu einer Liste von Anforderungen gibt diese Operation zur�ck,
	 * ob es freie zimmer gibt die diese Anforderungen erf�llen. Falls es diese
	 * gibt, kommt ein wahrer Wert zur�ck. Bei technischen Fehlern (z.B. keine
	 * Verbindung zur Persistenz) wird ein TechnischerFehlerException geworfen.
	 * 
	 * @param anforderungsliste
	 *            (Nicht null) Liste der Anforderungen.
	 * @return BooleanTyp
	 * @throws TechnischerFehlerException
	 */
	BooleanTyp gibtFreieAnforderungserf�llendeZimmer(
			AnforderungslisteTyp anforderungsliste)
			throws TechnischerFehlerException;

	/**
	 * (Abfrage) Diese Operation gibt f�r Anforderungen der hohen Preisklasse
	 * eine Liste der freien Zimmer zur�ck, die diese Anforderungen erf�llen.
	 * Enhalten die Anforderungen nicht die hohe Preisklasse, dann wird eine
	 * UnerlaubteZimmerauswahlException geworfen. Erf�llt kein Zimmer die
	 * Anforderungen, wird eine leere Liste zur�ckgegeben. Bei technischen
	 * Fehlern (z.B. keine Verbindung zur Persistenz) wird ein
	 * TechnischerFehlerException geworfen.
	 * 
	 * @param anforderungsliste
	 *            (Nicht null) Liste der Anforderungen
	 * @return ZimmerNrListeTyp
	 * @throws UnerlaubteZimmerauswahlException
	 * @throws TechnischerFehlerException
	 */
	ZimmerNrListeTyp holeErf�llendeZimmerHoherPreisklasse(
			AnforderungslisteTyp anforderungsliste)
			throws UnerlaubteZimmerauswahlException, TechnischerFehlerException;

	/**
	 * (Abfrage) Diese Operation berechnet zu einem Zimmer dessen aktuellen
	 * Preis. Bei technischen Fehlern (z.B. keine Verbindung zur Persistenz)
	 * wird ein TechnischerFehlerException geworfen.
	 * 
	 * @param zimmerNr
	 *            (Nicht null) ZimmerNr dessen Preis zu berechnen ist
	 * @return PreisTyp. Preis des Zimmers
	 * @throws TechnischerFehlerException
	 */
	PreisTyp berechnePreis(ZimmerNrTyp zimmerNr)
			throws TechnischerFehlerException;

	/**
	 * (Kommando) Registriert einen neuen Gast mit den gegebenen Informationen.
	 * Diese Operation kann fehlschlagen, falls bereits ein Gast mit dem
	 * gleichen Namen bereits vorhanden ist. Bei technischen Fehlern (z.B. keine
	 * Verbindung zur Persistenz) wird ein TechnischerFehlerException geworfen.
	 * 
	 * @param vorname
	 *            (Nicht null) Der Vorname des Gastes
	 * @param nachname
	 *            (Nicht null) Der Nachname des Gastes
	 * @param isErwachsen
	 *            (Nicht null) Ist der Gast ein Erwachsener?
	 * @param hatBehinderung
	 *            (Nicht null) Hat der Gast eine Behinderung?
	 * @return GastNrTyp. Die GastNr des neuen registrierten Gastes
	 * @throws GastRegistrierungFehlgeschlagenException
	 * @throws TechnischerFehlerException
	 */
	GastNrTyp registriereGast(NameTyp vorname, NameTyp nachname,
			BooleanTyp isErwachsen, BooleanTyp hatBehinderung)
			throws GastRegistrierungFehlgeschlagenException,
			TechnischerFehlerException;

	/**
	 * (Abfrage) F�r einen bereits registrierten Gast, kriegt man mit dieser
	 * Operation dessen GastNr zur�ck. Bei technischen Fehlern (z.B. keine
	 * Verbindung zur Persistenz) wird ein TechnischerFehlerException geworfen.
	 * 
	 * @param vorname
	 *            (Nicht null) Der Vorname des Gastes
	 * @param nachname
	 *            (Nicht null) Der Nachname des Gastes
	 * @param isErwachsen
	 *            (Nicht null) Ist der Gast ein Erwachsener?
	 * @param hatBehinderung
	 *            (Nicht null) Hat der Gast eine Behinderung?
	 * @return GastNrTyp. Die GastNr des genannten Gastes
	 * @throws GastNichtGefundenException
	 * @throws TechnischerFehlerException
	 */
	GastNrTyp holeGast(NameTyp vorname, NameTyp nachname,
			BooleanTyp isErwachsen, BooleanTyp hatBehinderung)
			throws GastNichtGefundenException, TechnischerFehlerException;

	/**
	 * (Abfrage) Pr�ft ob die zu reservierende G�ste existieren und stellt
	 * sicher, dass die Liste eine g�ltige Menge an reservierenden G�sten
	 * darstellt. Sind die reservierenden G�ste ung�ltig (z.B. Mehrfachvorkommen
	 * des gleichen Gastes oder die endg�ltige Liste ist leer.), dann wird eine
	 * Ung�ltigeReservierendeG�steException geworfen. Bei technischen Fehlern
	 * (z.B. keine Verbindung zur Persistenz) wird ein
	 * TechnischerFehlerException geworfen.
	 * 
	 * @param reservierendeG�ste
	 *            (Nicht null) Eine primitive Liste der reservierenden G�sten.
	 *            Die Liste darf auch nicht leer sein.
	 * @return ReserveriendeG�steNrTyp. Eine fachliche Liste der reservierenden
	 *         G�sten. Diese Liste ist f�r die Reservierungsoperation notwendig.
	 * @throws Ung�ltigeReservierendeG�steException
	 *             Diese Exception f�gt die F�lle f�r ung�ltige G�stelisten oder
	 *             f�r unregistrierte G�ste ab.
	 * @throws TechnischerFehlerException
	 */
	ReserveriendeG�steNrTyp validiereG�ste(List<GastNrTyp> reservierendeG�ste)
			throws Ung�ltigeReservierendeG�steException,
			TechnischerFehlerException;

	/**
	 * (Abfrage) Die Zahlungsinformationen werden validiert. Bei Erfolg kommt
	 * ein wahrer Wert zur�ck. Bei technischen Fehlern (z.B. keine Verbindung
	 * zur Persistenz) wird ein TechnischerFehlerException geworfen.
	 * 
	 * @param zahlungsinformation
	 *            (Nicht null) Bitcoin/Kreditkarte sowie begleitende Information
	 * @return BooleanTyp
	 * @throws TechnischerFehlerException
	 */
	BooleanTyp validiereZahlunginformation(
			ZahlungsinformationsTyp zahlungsinformation)
			throws TechnischerFehlerException;

	/**
	 * (Kommando) Delegiert die Transaktion an das Buchungsnachbarsystem. Das
	 * Nachbarsystem �bernimmt eine weitere Validierung vor der Pr�fung. Bei
	 * technischen Fehlern (z.B. keine Verbindung zur Persistenz) wird ein
	 * TechnischerFehlerException geworfen.
	 * 
	 * @param zahlungsinformation
	 *            (Nicht null) Bitcoin/Kreditkarte sowie begleitende Information
	 * @throws ZahlungFehlgeschlagenException
	 *             Wird geworfen falls die Buchung kritisch fehlschl�gt. Die
	 *             Exception enth�lt dann weitere Information zur Transaktion.
	 * @throws TechnischerFehlerException
	 */
	void bucheZahlung(ZahlungsinformationsTyp zahlungsinformation)
			throws ZahlungFehlgeschlagenException, TechnischerFehlerException;

	/**
	 * (Kommando) Diese Operation w�hlt f�r den Gast (mittlere/niedriger
	 * Preisklasse) ein Zimmer mit den gegebenen Anforderungen aus. Bei Erfolg
	 * wird ein Identifikator der Reservierung zur�ckgegeben. Sonst wird eine
	 * FehlgeschlageneReservierungException geworfen. (@pre) Die Anforderungen
	 * beziehen sich auf die hohe Preisklasse. Bei technischen Fehlern (z.B.
	 * keine Verbindung zur Persistenz) wird ein TechnischerFehlerException
	 * geworfen.
	 * 
	 * @param zahlungsinformation
	 *            (Nicht null) Die Information die das Buchhaltungsnachbarsystem
	 *            f�r die Transaktion ben�tigt.
	 * @param reservierendePersonen
	 *            (Nicht null) Eine Liste der G�ste die zu dieser Reservierung
	 *            geh�ren.
	 * @param anforderungsliste
	 *            (Nicht null) Liste der Anforderungen
	 * @return ReservierungsNrTyp. Eine Nummer die die Reservierung darstellt.
	 * @throws FehlgeschlageneReservierungException
	 * @throws TechnischerFehlerException
	 */
	ReservierungsNrTyp reserviereZimmerMitAnforderungen(
			ZahlungsinformationsTyp zahlungsinformation,
			ReserveriendeG�steNrTyp reservierendePersonen,
			AnforderungslisteTyp anforderungsliste)
			throws FehlgeschlageneReservierungException,
			TechnischerFehlerException;

	/**
	 * (Kommando) In der hohen Preisklasse wird diese Operation das vom Gast
	 * gew�hlte Zimmer reservieren. Bei Erfolg wird ein Identifikator der
	 * Reservierung zur�ckgegeben. Sonst wird eine
	 * FehlgeschlageneReservierungException geworfen. Bei technischen Fehlern
	 * (z.B. keine Verbindung zur Persistenz) wird ein
	 * TechnischerFehlerException geworfen.
	 * 
	 * @param zahlungsinformation
	 *            (Nicht null) Die Information die das Buchhaltungsnachbarsystem
	 *            f�r die Transaktion ben�tigt.
	 * @param reservierendePersonen
	 *            (Nicht null) Eine Liste der G�ste die zu dieser Reservierung
	 *            geh�ren.
	 * @param zimmerNr
	 *            (Nicht null) Die ZimmerNr des zu reservierenden Zimmers
	 * @return ReservierungsNrTyp. Eine Nummer die die Reservierung darstellt.
	 * @throws FehlgeschlageneReservierungException
	 * @throws TechnischerFehlerException
	 */
	ReservierungsNrTyp reserviereAusgew�hltesZimmer(
			ZahlungsinformationsTyp zahlungsinformation,
			ReserveriendeG�steNrTyp reservierendePersonen, ZimmerNrTyp zimmerNr)
			throws FehlgeschlageneReservierungException,
			TechnischerFehlerException;
}