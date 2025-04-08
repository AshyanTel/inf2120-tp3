

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

/**
 * Implementation d'un type de donnees abstrait de {\code ListeDoublementChaine} a l'aide d'un double chainage.
 *
 * Les positions dans la {\code ListeDoublementChaine} sont designees par des entiers.  L'element a la premiere
 * case de la liste est a la position {\code 0} et l'element a la derniere case est a la position
 * {\code taille() - 1}.
 * @param <E> Le type des elements places dans la {\code ListeDoublementChaine}.
 */
public class ListeDoublementChaine< E >
        implements Iterable< E > {
    private static final int POSITION_MINIMUM_INFERIEUR = 0;
    /**
     * Une classe interne pour representer un {\code Chainon} contenant un element.
     * Il contient aussi deux references.  Une vers le {\code Chainon} precedant et une autre
     * vers le {\code Chainon} suivant.
     */
    private static class Chainon< E > {
        public E element;
        public Chainon< E > precedant;
        public Chainon< E > suivant;

        public Chainon( Chainon<E > precedant, E element ) {
            this.element = element;
            this.precedant = precedant;
        }

        public Chainon( Chainon< E > precedant, E element, Chainon< E > suivant ) {
            this.element = element;
            this.precedant = precedant;
            this.suivant = suivant;
        }
    }

    private static class ListeIterator< E > implements Iterator< E > {

        private Chainon< E > courant;

        public ListeIterator( Chainon< E > tete ) {
            courant = tete;
        }

        @Override
        public boolean hasNext() {
            return null != courant;
        }

        @Override
        public E next() {
            E element = courant.element;
            courant = courant.suivant;
            return element;
        }

    }

    static private class ListeSpliterator< E > implements Spliterator< E > {
        private static final int CHARACTERISTIQUES = ORDERED | SIZED | SUBSIZED;
        private static final int TAILLE_MINIMAL_SPLIT = 5;

        private int debut;
        private int fin;
        private Chainon< E > courant;

        public ListeSpliterator( int debut, int fin, Chainon< E > courant ) {
            this.debut = debut;
            this.fin = fin;
            this.courant = courant;
        }

        @Override
        public boolean tryAdvance( Consumer< ? super E > action ) {
            boolean resultat = false;

            if( debut < fin ) {
                resultat = true;
                action.accept( courant.element );
                courant = courant.suivant;
                ++ debut;
            }

            return resultat;
        }

        @Override
        public Spliterator< E > trySplit() {
            ListeSpliterator< E > resultat = null;

            if( TAILLE_MINIMAL_SPLIT <= ( debut - fin ) ) {
                int milieu = ( debut + fin ) / 2;
                resultat = new ListeSpliterator<>( debut, milieu, courant );
                for( int i = debut; i <= milieu; ++ i ) {
                    courant = courant.suivant;
                }
                debut = milieu + 1;
            }

            return resultat;
        }

        @Override
        public long estimateSize() {
            return fin - debut;
        }

        @Override
        public int characteristics() {
            return CHARACTERISTIQUES;
        }
    }

    /**
     * methode interne utilisee pour retrouver un {\code Chainon} selon sa position.
     * @param position La position du {\code Chainon}.  Le premier {\code Chainon}
     *                 est a la position {\code 0}.
     * @return Le {\code Chainon} demande.
     * @throws IndexOutOfBoundsException Lance si la position est invalide : {\code position < 0 || taille() <= position}.
     */
    private Chainon< E > getChainon( int position )
            throws IndexOutOfBoundsException {
        if( position < POSITION_MINIMUM_INFERIEUR || taille <= position  ) {
            throw new IndexOutOfBoundsException();
        }

        Chainon< E > courant = tete;

        while( position != 0 ) {
            courant = courant.suivant;
            -- position;
        }

        return courant;
    }


    /**
     * Une reference au premier element de la {\code ListeDoublementChaine} est maintenu ici.
     */
    protected Chainon< E > tete;

    /**
     * Puisque l'ajout a la fin d'une {\code ListeDoublementChaine} est courante, un reference est
     * conserve pour obtenir rapidement le dernier element.
     */
    protected Chainon< E > fin;

    protected int taille;


    /**
     * Construit une {\code ListeDoublementChaine} vide.
     */
    public ListeDoublementChaine() {
        tete = null;
        taille = 0;
    }

    /**
     * Construit une {\code ListeDoublementChaine} à l'aide d'une {\code Collection} d'éléments.
     */
    public ListeDoublementChaine( Collection< E > elements ) {
        this();

        for( E element : elements ) {
            inserer( element );
        }
    }


    @Override
    public boolean equals( Object droite ) {
        return switch( droite ) {
            case ListeDoublementChaine temp -> {

                Iterator< E > itGauche = this.iterator();
                Iterator< E > itDroite = temp.iterator();

                while( itGauche.hasNext() && itDroite.hasNext()
                       && itGauche.next().equals( itDroite.next() ) );

                yield ! ( itGauche.hasNext() || itDroite.hasNext() );
            }
            default -> false;
        };
    }


    /**
     * Donne l'element a la position indiquee.
     * @param position La position de l'element a extraire.
     * @return L'element extrait.
     * @throws IndexOutOfBoundsException Lance si la position est invalide : {\code position < 0 || taille() <= position}.
     */
    public E get( int position )
            throws IndexOutOfBoundsException {
        return getChainon( position ).element;
    }


    /**
     * Ajoute un element a la fin de la {\code ListeDoublementChaine}
     * @param element L'element a ajouter.
     */
    public void inserer( E element ) {
        Chainon< E > nouveau = new Chainon<>( fin, element );

        if( taille == 0 ) {
            tete = nouveau;
        } else {
            fin.suivant = nouveau;
        }

        fin = nouveau;

        ++ taille;
    }


    /**
     * Ajoute un element dans la {\code ListeDoublementChaine} a la position indiquee.
     * @param position La position ou l'element est ajoute.  Les valeurs au position suivante seront
     *                 decalees de une case.  Un ajout a la position {\code taille()} ajoute l'element a la
     *                 fin de la {\code ListeDoublementChaine}.
     * @param element L'element a ajouter.
     * @throws IndexOutOfBoundsException Lance si la position est invalide : {\code position < 0 || taille() < position}.
     */
    public void inserer( int position, E element )
            throws IndexOutOfBoundsException {
        if( position == taille ) {
            inserer( element );
        } else {
            Chainon< E > courant = getChainon( position );

            Chainon< E > nouveau = new Chainon<>( courant.precedant, element, courant );

            if( courant.precedant == null ) {
                tete = nouveau;
            } else {
                courant.precedant.suivant = nouveau;
            }
            courant.precedant = nouveau;

            ++ taille;
        }
    }


    /**
     * genere un {\code Iterator}
     * @return Un {\code Iterator}
     */
    @Override
    public Iterator< E > iterator() {
        return new ListeIterator<>( tete );
    }


    /**
     * Construit un {\code Stream} pour la {\code Liste}.
     *
     * IMPORTANT : ce Stream n'est pas protégé contre les effets qui modifient la Liste lors
     * de l'activation du Stream.
     * @return Un Stream sur la liste
     */
    public Stream< E > stream() {
        return StreamSupport.stream( () -> new ListeSpliterator<>( 0, taille, tete ),
                ListeSpliterator.CHARACTERISTIQUES, false );
    }


    /**
     * Construit un {\code Stream} pour la {\code Liste}.
     *
     * IMPORTANT : ce Stream n'est pas protégé contre les effets qui modifient la Liste lors
     * de l'activation du Stream.
     * @return Un Stream sur la liste
     */
    public Stream< E > parallelStream() {
        return StreamSupport.stream( () -> new ListeSpliterator<>( 0, taille, tete ),
                ListeSpliterator.CHARACTERISTIQUES, true );
    }


    /**
     * Modifie l'element a la position indiquee.
     * @param position La position de l'element a modifier.
     * @param element L'element qui va remplacer l'ancien element.
     * @throws IndexOutOfBoundsException Lance si la position est invalide : {\code position < 0 || taille() <= position}.
     */
    public void set( int position, E element )
            throws IndexOutOfBoundsException {
        getChainon( position ).element = element;
    }


    /**
     * Enleve un element de la {\code ListeDoublementChaine}
     * @param position La position de l'element a supprimer.
     * @throws IndexOutOfBoundsException Lance si la position est invalide : {\code position < 0 || taille() <= position}.
     */
    public void supprimer( int position )
            throws IndexOutOfBoundsException {
        Chainon< E > courant = getChainon( position );

        if( null == courant.precedant ) {
            tete = courant.suivant;
        } else {
            courant.precedant.suivant = courant.suivant;
        }

        if( null == courant.suivant ) {
            fin = courant.precedant;
        } else {
            courant.suivant.precedant = courant.precedant;
        }

        -- taille;
    }


    /**
     * La taille de la {\code ListeDoublementChaine}.
     * @return Le nombre d'element que contient la {\code ListeDoublementChaine}.
     */
    public int taille() {
        return taille;
    }


    @Override
    public String toString() {
        return stream()
                .map( ( e ) -> e.toString() )
                .collect( Collectors.joining( ", ", "[ ", " ]" ) );
    }


    /***************************************************************/
    /***************************************************************/
    /***************************************************************/


    /**
     * Construit une nouvelle liste avec les éléments de la liste originale (this)
     * qui donnent True pour le prédicat.
     * La nouvelle liste est retournée par la méthode.
     * Aussi, les éléments qui ont donnés True sont enlevé de la liste originale.
     * @param condition Le prédicat qu'un élément doit respecter pour être accepté dans la nouvelle liste.
     *                  Le comportement de la méthode n'est pas définie si ce paramètre est {\code null}.
     * @return La liste des éléments qui ont donnés True.
     */
    public ListeDoublementChaine< E > extraireSi( Predicate< E > condition ) {
        Iterator< E > iterator = iterator();
        ListeDoublementChaine <E> elementsExtraits = new ListeDoublementChaine<>();
        int i = 0;

        while( iterator.hasNext() ) {
            E element = iterator.next();

            if(condition.test(element)) {
                supprimer(i);
                elementsExtraits.inserer( element );
                --i;
            }
            ++i;
        }
        return elementsExtraits;
    }


    /**
     * Vérifie si les éléments de la liste originale (this) sont présent
     * dans la liste en paramètre.
     * @param droite La liste dans laquelle la méthode cherche les éléments de la liste
     *               originale.  Le comportement de la méthode n'est par définie si ce
     *               paramètre est {\code null}.
     * @return True si tous les éléments de la liste orginale sont présents dans la liste
     * en paramètre.  False sinon.
     */
    public boolean sousEnsembleEgal( ListeDoublementChaine< E > droite ) {
        boolean estSousEnsemble = true;
        Iterator< E > iterator = iterator();
        while( estSousEnsemble && iterator.hasNext()) {
            E element = iterator.next();
            Iterator< E > droiteIterator = droite.iterator();
            boolean trouve = false;
            while( droiteIterator.hasNext() ) {
                if( element.equals( droiteIterator.next() ) ) {
                    trouve = true;
                }
            }
            estSousEnsemble = trouve;
        }
        return estSousEnsemble;
    }


    /**
     * Vérifie que les éléments d'une liste sont placés de tel sorte que les éléments qui
     * donne True selon le prédicat viennent tous avant les éléments qui donnes False.
     * <p>
     * Cas spéciaux :
     * <ul>
     *     <li>Une liste vide donne toujours True.</li>
     *     <li>Si tous les éléments de la liste donne True, alors le résultat est True.</li>
     *     <li>Si tous les éléments de la liste donne False, alors le résultat est True.</li>
     * </ul>
     * </p>
     * @param condition Le prédicat utilisé pour vérifier si la liste est divisée correctement.
     *                  Le comportement de la méthode n'est pas défini si ce paramètre est {\code null}.
     * @return True si la liste est divisé selon la condition en paramètre.
     */
    public boolean estDiviseSelon( Predicate< E > condition ) {
        boolean result = true;
        if(taille > 1) {
            Iterator< E > iterator = iterator();
            E element = iterator.next();

            boolean last = condition.test( element );
            int changement = 0;

            while( iterator.hasNext()) {
                boolean current = condition.test(iterator.next());
                if( current != last ) {
                    last = current;
                    ++changement;
                }
            }

            if(changement > 1){
                result = false;
            }
        }
        return result;
    }


    /**
     * Déplace les éléments de la liste pour que la méthode {\code estDiviseSelon} puisse donner True.
     * Donc, après l'appel de cette méthode, les éléments qui donnent True avec le prédicat doivent
     * venir avant tous les éléments qui donnent False.
     * L'ordre relatif des éléments qui donnent True doit être conservé.
     * C'est à dire que pour toutes paire de deux éléments au position i et j respectivement,
     * si le prédicat donne True pour les deux éléments et que i < j avant le remaniment de la liste
     * alors i < j aussi après le remaniment de la liste.
     * Même chose pour les éléments qui donnent False, leurs ordre relatif doit être conservé.
     * @param condition Le prédicat utiliser pour diviser la liste.
     *                  Le comportement de la méthode n'est pas défini si ce paramètre est {\code null}.
     */
    public void diviserSelon( Predicate< E > condition ) {
        ListeDoublementChaine<E> avant = extraireSi( condition );
        int i = 0;
        for(E element : avant){
            inserer( i, element );
            ++i;
        }
    }
}
