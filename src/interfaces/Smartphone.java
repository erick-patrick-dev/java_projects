public class Smartphone implements VideoPlayer, MusicPlayer{

    @Override
    public void playMusic() {
        System.out.println("Tocando música");
    }

    @Override
    public void pauseMusic() {
        System.out.println("Pausar música");
    }

    @Override
    public void stopMusic() {
        System.out.println("Parando música");
    }

    @Override
    public void playVideo() {
        System.out.println("Reproduzir video");
    }

    @Override
    public void pauseVideo() {
        System.out.println("Pausar video");
    }

    @Override
    public void stopVideo() {
        System.out.println("Parar video");
    }
}

// Só pode herdar uma classe por vez, mas interface tem liberdade para implementar
//   quantas quiser.