import { StatusBar } from "expo-status-bar";
import { useEffect } from "react";
import {
  Button,
  StyleSheet,
  Text,
  View,
  NativeEventEmitter,
} from "react-native";
import { NativeModules } from "react-native";
const { CalendarModule } = NativeModules;
const eventEmitter = new NativeEventEmitter(CalendarModule);

export default function App() {
  const createCalendarEventPromise = async () => {
    try {
      let res = await CalendarModule.createCalendarPromise();
      console.log(res);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    eventEmitter.addListener("EventCount", (eventCount) => {
      console.log(eventCount);
    });

    return () => {
      eventEmitter.removeAllListeners("EventCount");
    };
  }, []);

  return (
    <View style={styles.container}>
      <Text>Open up App.tsx to start working on your app!</Text>
      <StatusBar style="auto" />

      <Button
        title="Calendar Event Promise"
        onPress={createCalendarEventPromise}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
