import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ListeDoublementChaineTest {

    @Test
    void extraireSi1() {
        ListeDoublementChaine< Integer > listeAvant =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9 )
                );
        ListeDoublementChaine< Integer > listeApres =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 5, 7, 9 )
                );
        ListeDoublementChaine< Integer > listeAttendu =
                new ListeDoublementChaine<>(
                        Arrays.asList( 2, 4, 6, 8 )
                );

        assertEquals( listeAttendu, listeAvant.extraireSi( ( x ) -> ( x % 2 ) == 0 ) );
        assertEquals( listeApres, listeAvant );
    }

    @Test
    void extraireSi2() {
        ListeDoublementChaine< Integer > listeAvant =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 5, 7, 9 )
                );
        ListeDoublementChaine< Integer > listeApres =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 5, 7, 9 )
                );
        ListeDoublementChaine< Integer > listeAttendu =
                new ListeDoublementChaine<>();

        assertEquals( listeAttendu, listeAvant.extraireSi( ( x ) -> ( x % 2 ) == 0 ) );
        assertEquals( listeApres, listeAvant );
    }

    @Test
    void extraireSi3() {
        ListeDoublementChaine< Integer > listeAvant =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, -2, -3, 4, 5, -6, -7, 8, 9 )
                );
        ListeDoublementChaine< Integer > listeApres =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 4, 5, 8, 9 )
                );
        ListeDoublementChaine< Integer > listeAttendu =
                new ListeDoublementChaine<>(
                        Arrays.asList( -2, -3, -6, -7 )
                );

        assertEquals( listeAttendu, listeAvant.extraireSi( ( x ) -> x < 0 ) );
        assertEquals( listeApres, listeAvant );
    }

    @Test
    void extraireSi4() {
        ListeDoublementChaine< Integer > listeAvant =
                new ListeDoublementChaine<>(
                        Arrays.asList( -2, -3, -6, -7 )
                );
        ListeDoublementChaine< Integer > listeApres =
                new ListeDoublementChaine<>(
                );
        ListeDoublementChaine< Integer > listeAttendu =
                new ListeDoublementChaine<>(
                        Arrays.asList( -2, -3, -6, -7 )
                );

        assertEquals( listeAttendu, listeAvant.extraireSi( ( x ) -> x < 0 ) );
        assertEquals( listeApres, listeAvant );
    }

    @Test
    void extraireSi5() {
        ListeDoublementChaine< Integer > listeAvant =
                new ListeDoublementChaine<>();
        ListeDoublementChaine< Integer > listeApres =
                new ListeDoublementChaine<>();
        ListeDoublementChaine< Integer > listeAttendu =
                new ListeDoublementChaine<>();

        assertEquals( listeAttendu, listeAvant.extraireSi( ( x ) -> x < 0 ) );
        assertEquals( listeApres, listeAvant );
    }

    @Test
    void sousEnsembleEgal1() {
        ListeDoublementChaine< Integer > listeSous =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 5, 7, 9 )
                );
        ListeDoublementChaine< Integer > listeSousCopie =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 5, 7, 9 )
                );
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9 )
                );
        ListeDoublementChaine< Integer > listeCopie =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9 )
                );

        assertTrue( listeSous.sousEnsembleEgal( liste ) );
        assertFalse( liste.sousEnsembleEgal( listeSous ) );
        assertEquals( listeSous, listeSousCopie );
        assertEquals( liste, listeCopie );
    }

    @Test
    void sousEnsembleEgal2() {
        ListeDoublementChaine< Integer > listeSous =
                new ListeDoublementChaine<>();
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9 )
                );
        ListeDoublementChaine< Integer > listeCopie =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9 )
                );

        assertTrue( listeSous.sousEnsembleEgal( liste ) );
        assertFalse( liste.sousEnsembleEgal( listeSous ) );
        assertEquals( 0, listeSous.taille() );
        assertEquals( liste, listeCopie );
    }

    @Test
    void sousEnsembleEgal3() {
        ListeDoublementChaine< Integer > listeSous =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 2, 3, 4, 5 )
                );
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 2, 3, 4, 5 )
                );

        assertTrue( listeSous.sousEnsembleEgal( liste ) );
        assertTrue( liste.sousEnsembleEgal( listeSous ) );
    }

    @Test
    void sousEnsembleEgal4() {
        ListeDoublementChaine< Integer > listeSous =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 1, 2, 2, 2, 2, 2 )
                );
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 2, 3, 4, 5 )
                );

        assertTrue( listeSous.sousEnsembleEgal( liste ) );
        assertFalse( liste.sousEnsembleEgal( listeSous ) );
    }

    @Test
    void sousEnsembleEgal5() {
        ListeDoublementChaine< Integer > listeSous =
                new ListeDoublementChaine<>();
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>();

        assertTrue( listeSous.sousEnsembleEgal( liste ) );
    }

    @Test
    void estDiviseSelon1() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 1, 3, 5, 7 )
                );
        ListeDoublementChaine< Integer > listeCopie =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 1, 3, 5, 7 )
                );

        assertTrue( liste.estDiviseSelon( ( e ) -> e < 2 ) );
        assertEquals( liste, listeCopie );
    }

    @Test
    void estDiviseSelon2() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 3, 5, 1 )
                );
        ListeDoublementChaine< Integer > listeCopie =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 3, 5, 1 )
                );

        assertFalse( liste.estDiviseSelon( ( e ) -> e < 2 ) );
        assertEquals( liste, listeCopie );
    }

    @Test
    void estDiviseSelon3() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 2 )
                );
        ListeDoublementChaine< Integer > listeCopie =
                new ListeDoublementChaine<>(
                        Arrays.asList( 2 )
                );

        assertTrue( liste.estDiviseSelon( ( e ) -> ( e % 2 ) == 0 ) );
        assertEquals( liste, listeCopie );
    }

    @Test
    void estDiviseSelon4() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>();

        assertTrue( liste.estDiviseSelon( ( e ) -> ( e % 2 ) == 0 ) );
        assertEquals( 0, liste.taille() );
    }

    @Test
    void estDiviseSelon5() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 3, 5, 1 )
                );
        ListeDoublementChaine< Integer > listeCopie =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 3, 5, 1 )
                );

        assertTrue( liste.estDiviseSelon( ( e ) -> 0 < e ) );
        assertEquals( liste, listeCopie );
    }

    @Test
    void estDiviseSelon6() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 3, 5, 1 )
                );
        ListeDoublementChaine< Integer > listeCopie =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 3, 3, 5, 1 )
                );

        assertTrue( liste.estDiviseSelon( ( e ) -> ( e % 2 ) == 0 ) );
        assertEquals( liste, listeCopie );
    }

    @Test
    void estDiviseSelon7() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1 )
                );
        ListeDoublementChaine< Integer > listeCopie =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1 )
                );

        assertTrue( liste.estDiviseSelon( ( e ) -> e != 1 ) );
        assertEquals( liste, listeCopie );
    }

    @Test
    void diviserSelon1() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9 )
                );
        ListeDoublementChaine< Integer > listeResultat =
                new ListeDoublementChaine<>(
                        Arrays.asList( 2, 4, 6, 8, 1, 3, 5, 7, 9 )
                );

        liste.diviserSelon( ( e ) -> ( e % 2 ) == 0 );

        assertEquals( listeResultat, liste );
    }

    @Test
    void diviserSelon2() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>();
        ListeDoublementChaine< Integer > listeResultat =
                new ListeDoublementChaine<>();

        liste.diviserSelon( ( e ) -> ( e % 2 ) == 0 );

        assertEquals( listeResultat, liste );
    }

    @Test
    void diviserSelon3() {
        ListeDoublementChaine< Integer > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1 )
                );
        ListeDoublementChaine< Integer > listeResultat =
                new ListeDoublementChaine<>(
                        Arrays.asList( 1 )
                );

        liste.diviserSelon( ( e ) -> e < 100 );

        assertEquals( listeResultat, liste );
    }

    @Test
    void diviserSelon4() {
        ListeDoublementChaine< String > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( "allo", "a" )
                );
        ListeDoublementChaine< String > listeResultat =
                new ListeDoublementChaine<>(
                        Arrays.asList( "a", "allo" )
                );

        liste.diviserSelon( ( e ) -> e.length() == 1 );

        assertEquals( listeResultat, liste );
    }

    @Test
    void diviserSelon5() {
        ListeDoublementChaine< String > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( "a", "n", "du", "e" )
                );
        ListeDoublementChaine< String > listeResultat =
                new ListeDoublementChaine<>(
                        Arrays.asList( "a", "n", "e", "du" )
                );

        liste.diviserSelon( ( e ) -> e.length() == 1 );

        assertEquals( listeResultat, liste );
    }

    @Test
    void diviserSelon6() {
        ListeDoublementChaine< String > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( "comment", "ni", "devra", "etre" )
                );
        ListeDoublementChaine< String > listeResultat =
                new ListeDoublementChaine<>(
                        Arrays.asList( "etre", "comment", "ni", "devra" )
                );

        liste.diviserSelon( ( e ) -> e.length() == 4 );

        assertEquals( listeResultat, liste );
    }

    @Test
    void diviserSelon7() {
        ListeDoublementChaine< String > liste =
                new ListeDoublementChaine<>(
                        Arrays.asList( "etre", "comment", "ni", "devra" )
                );
        ListeDoublementChaine< String > listeResultat =
                new ListeDoublementChaine<>(
                        Arrays.asList( "comment", "ni", "devra", "etre" )
                );

        liste.diviserSelon( ( e ) -> e.length() != 4 );

        assertEquals( listeResultat, liste );
    }
}