import java.util.ArrayList;
import java.util.List;

public class Publisher implements ISubject {

        private List<IObserver> gozlemleyici = new ArrayList<>();

        public void attach(IObserver subscriber) {
            gozlemleyici.add(subscriber);
        }
         public void detach(IObserver subscriber) {
             gozlemleyici.remove(subscriber);
        }
        public void notify(String mesaj) {
            for(IObserver _gozlemleyici: gozlemleyici) {
                _gozlemleyici.update(mesaj);
            }
        }
    }

