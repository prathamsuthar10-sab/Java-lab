interface Switchable {
    void on();
    void off();
    boolean isOn();

    default void toggle() {
        if (isOn()) {
            off();
        } else {
            on();
        }
    }
}

class Fan implements Switchable {
    private boolean running = false;

    public void on() {
        running = true;
        System.out.println("Fan is ON");
    }

    public void off() {
        running = false;
        System.out.println("Fan is OFF");
    }

    public boolean isOn() {
        return running;
    }
}

class Light implements Switchable {
    private boolean glowing = false;

    public void on() {
        glowing = true;
        System.out.println("Light is ON");
    }

    public void off() {
        glowing = false;
        System.out.println("Light is OFF");
    }

    public boolean isOn() {
        return glowing;
    }
}

@FunctionalInterface
interface SwitchPermission {
    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] devices = {new Fan(), new Light()};

        System.out.println("First toggle:");
        for (Switchable device : devices) {
            device.toggle();
        }

        System.out.println("\nSecond toggle:");
        for (Switchable device : devices) {
            device.toggle();
        }

        SwitchPermission anonymousRule = new SwitchPermission() {
            public boolean maySwitchOn(Switchable device, int hour) {
                if (device instanceof Fan) {
                    return hour >= 6 && hour < 22;
                }
                return hour >= 17 && hour < 23;
            }
        };

        System.out.println("\nAnonymous class at 20:00:");
        for (Switchable device : devices) {
            if (anonymousRule.maySwitchOn(device, 20)) {
                device.on();
            }
        }

        for (Switchable device : devices) {
            device.off();
        }

        SwitchPermission lambdaRule = (device, hour) ->
                device instanceof Fan || (hour >= 6 && hour < 18);

        System.out.println("\nLambda at 23:00:");
        for (Switchable device : devices) {
            if (lambdaRule.maySwitchOn(device, 23)) {
                device.on();
            } else {
                System.out.println(
                    device.getClass().getSimpleName() + " is not allowed ON"
                );
            }
        }
    }
}