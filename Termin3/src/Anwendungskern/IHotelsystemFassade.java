package Anwendungskern;

import java.util.List;

import Exceptions.FehlgeschlageneReservierungException;
import Exceptions.GastNichtGefundenException;
import Exceptions.GastRegistrierungFehlgeschlagenException;
import Exceptions.TechnischerFehlerException;
import Exceptions.UnerlaubteZimmerauswahlException;
import Exceptions.UngültigeReservierendeGästeException;
import Exceptions.ZahlungFehlgeschlagenException;
import FachlicheTypen.AnforderungslisteTyp;
import FachlicheTypen.BooleanTyp;
import FachlicheTypen.GastNrTyp;
import FachlicheTypen.NameTyp;
import FachlicheTypen.PreisTyp;
import FachlicheTypen.ReserveriendeGästeNrTyp;
import FachlicheTypen.ReservierungsNrTyp;
import FachlicheTypen.ZahlungsinformationsTyp;
import FachlicheTypen.ZimmerNrListeTyp;
import FachlicheTypen.ZimmerNrTyp;

public interface IHotelsystemFassade {
	/*
	 * Die Operationen für den "Zimmer reservieren Use-Case" werden in der
	 * folgenden Reihenfolge durchgeführt:
	 * 
	 * 1.a BooleanTyp gibtFreieAnforderungserfüllendeZimmer(
	 * AnforderungslisteTyp anforderungsliste) throws
	 * TechnischerFehlerException;
	 * 
	 * 1.b (optional) ZimmerNrListeTyp holeErfüllendeZimmerHoherPreisklasse(
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
	 * ZahlungsinformationsTyp zahlungsinformation, ReserveriendeGästeNrTyp
	 * reservierendePersonen, AnforderungslisteTyp anforderungsliste) throws
	 * FehlgeschlageneReservierungException, TechnischerFehlerException;
	 * 
	 * 4.a2 ReservierungsNrTyp reserviereAusgewähltesZimmer(
	 * ZahlungsinformationsTyp zahlungsinformation, ReserveriendeGästeNrTyp
	 * reservierendePersonen, ZimmerNrTyp zimmerNr) throws
	 * FehlgeschlageneReservierungException, TechnischerFehlerException;
	 * 
	 * ( 4.a1 und 4.a2 sind einander ausschließend. )
	 */
	// Mehr Informationen finden sie im beigefügten README.pdf

	/**
	 * (Abfrage) Zu einer Liste von Anforderungen gibt diese Operation zurück,
	 * ob es freie zimmer gibt die diese Anforderungen erfüllen. Falls es diese
	 * gibt, kommt ein wahrer Wert zurück. Bei technischen Fehlern (z.B. keine
	 * Verbindung zur Persistenz) wird ein TechnischerFehlerException geworfen.
	 * 
	 * @param anforderungsliste
	 *            (Nicht null) Liste der Anforderungen.
	 * @return BooleanTyp
	 * @throws TechnischerFehlerException
	 */
	BooleanTyp gibtFreieAnforderungserfüllendeZimmer(
			AnforderungslisteTyp anforderungsliste)
			throws TechnischerFehlerException;

	/**
	 * (Abfrage) Diese Operation gibt für Anforderungen der hohen Preisklasse
	 * eine Liste der freien Zimmer zurück, die diese Anforderungen erfüllen.
	 * Enhalten die Anforderungen nicht die hohe Preisklasse, dann wird eine
	 * UnerlaubteZimmerauswahlException geworfen. Erfüllt kein Zimmer die
	 * Anforderungen, wird eine leere Liste zurückgegeben. Bei technischen
	 * Fehlern (z.B. keine Verbindung zur Persistenz) wird ein
	 * TechnischerFehlerException geworfen.
	 * 
	 * @param anforderungsliste
	 *            (Nicht null) Liste der Anforderungen
	 * @return ZimmerNrListeTyp
	 * @throws UnerlaubteZimmerauswahlException
	 * @throws TechnischerFehlerException
	 */
	ZimmerNrListeTyp holeErfüllendeZimmerHoherPreisklasse(
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
	 * (Abfrage) Für einen bereits registrierten Gast, kriegt man mit dieser
	 * Operation dessen GastNr zurück. Bei technischen Fehlern (z.B. keine
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
	 * (Abfrage) Prüft ob die zu reservierende Gäste existieren und stellt
	 * sicher, dass die Liste eine gültige Menge an reservierenden Gästen
	 * darstellt. Sind die reservierenden Gäste ungültig (z.B. Mehrfachvorkommen
	 * des gleichen Gastes oder die endgültige Liste ist leer.), dann wird eine
	 * UngültigeReservierendeGästeException geworfen. Bei technischen Fehlern
	 * (z.B. keine Verbindung zur Persistenz) wird ein
	 * TechnischerFehlerException geworfen.
	 * 
	 * @param reservierendeGäste
	 *            (Nicht null) Eine primitive Liste der reservierenden Gästen.
	 *            Die Liste darf auch nicht leer sein.
	 * @return ReserveriendeGästeNrTyp. Eine fachliche Liste der reservierenden
	 *         Gästen. Diese Liste ist für die Reservierungsoperation notwendig.
	 * @throws UngültigeReservierendeGästeException
	 *             Diese Exception fägt die Fälle für ungültige Gästelisten oder
	 *             für unregistrierte Gäste ab.
	 * @throws TechnischerFehlerException
	 */
	ReserveriendeGästeNrTyp validiereGäste(List<GastNrTyp> reservierendeGäste)
			throws UngültigeReservierendeGästeException,
			TechnischerFehlerException;

	/**
	 * (Abfrage) Die Zahlungsinformationen werden validiert. Bei Erfolg kommt
	 * ein wahrer Wert zurück. Bei technischen Fehlern (z.B. keine Verbindung
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
	 * Nachbarsystem übernimmt eine weitere Validierung vor der Prüfung. Bei
	 * technischen Fehlern (z.B. keine Verbindung zur Persistenz) wird ein
	 * TechnischerFehlerException geworfen.
	 * 
	 * @param zahlungsinformation
	 *            (Nicht null) Bitcoin/Kreditkarte sowie begleitende Information
	 * @throws ZahlungFehlgeschlagenException
	 *             Wird geworfen falls die Buchung kritisch fehlschlägt. Die
	 *             Exception enthält dann weitere Information zur Transaktion.
	 * @throws TechnischerFehlerException
	 */
	void bucheZahlung(ZahlungsinformationsTyp zahlungsinformation)
			throws ZahlungFehlgeschlagenException, TechnischerFehlerException;

	/**
	 * (Kommando) Diese Operation wählt für den Gast (mittlere/niedriger
	 * Preisklasse) ein Zimmer mit den gegebenen Anforderungen aus. Bei Erfolg
	 * wird ein Identifikator der Reservierung zurückgegeben. Sonst wird eine
	 * FehlgeschlageneReservierungException geworfen. (@pre) Die Anforderungen
	 * beziehen sich auf die hohe Preisklasse. Bei technischen Fehlern (z.B.
	 * keine Verbindung zur Persistenz) wird ein TechnischerFehlerException
	 * geworfen.
	 * 
	 * @param zahlungsinformation
	 *            (Nicht null) Die Information die das Buchhaltungsnachbarsystem
	 *            für die Transaktion benötigt.
	 * @param reservierendePersonen
	 *            (Nicht null) Eine Liste der Gäste die zu dieser Reservierung
	 *            gehören.
	 * @param anforderungsliste
	 *            (Nicht null) Liste der Anforderungen
	 * @return ReservierungsNrTyp. Eine Nummer die die Reservierung darstellt.
	 * @throws FehlgeschlageneReservierungException
	 * @throws TechnischerFehlerException
	 */
	ReservierungsNrTyp reserviereZimmerMitAnforderungen(
			ZahlungsinformationsTyp zahlungsinformation,
			ReserveriendeGästeNrTyp reservierendePersonen,
			AnforderungslisteTyp anforderungsliste)
			throws FehlgeschlageneReservierungException,
			TechnischerFehlerException;

	/**
	 * (Kommando) In der hohen Preisklasse wird diese Operation das vom Gast
	 * gewählte Zimmer reservieren. Bei Erfolg wird ein Identifikator der
	 * Reservierung zurückgegeben. Sonst wird eine
	 * FehlgeschlageneReservierungException geworfen. Bei technischen Fehlern
	 * (z.B. keine Verbindung zur Persistenz) wird ein
	 * TechnischerFehlerException geworfen.
	 * 
	 * @param zahlungsinformation
	 *            (Nicht null) Die Information die das Buchhaltungsnachbarsystem
	 *            für die Transaktion benötigt.
	 * @param reservierendePersonen
	 *            (Nicht null) Eine Liste der Gäste die zu dieser Reservierung
	 *            gehören.
	 * @param zimmerNr
	 *            (Nicht null) Die ZimmerNr des zu reservierenden Zimmers
	 * @return ReservierungsNrTyp. Eine Nummer die die Reservierung darstellt.
	 * @throws FehlgeschlageneReservierungException
	 * @throws TechnischerFehlerException
	 */
	ReservierungsNrTyp reserviereAusgewähltesZimmer(
			ZahlungsinformationsTyp zahlungsinformation,
			ReserveriendeGästeNrTyp reservierendePersonen, ZimmerNrTyp zimmerNr)
			throws FehlgeschlageneReservierungException,
			TechnischerFehlerException;
}