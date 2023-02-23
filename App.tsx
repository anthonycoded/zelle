import { StatusBar } from "expo-status-bar";
import { useEffect } from "react";
import { Button, StyleSheet, Text, View, Platform } from "react-native";
import { NativeModules } from "react-native";

export default function App() {
  const buttonClicked = () => {
    if (Platform.OS === "android") {
      const openZelle = NativeModules?.OpenZelleModule;

      try {
        openZelle.open();
      } catch (error) {
        console.log(error);
      }
    } else if (Platform.OS === "ios") {
      console.log(NativeModules?.Counter);
      NativeModules?.ZelleModule?.Increment();
    }
  };

  useEffect(() => {}, []);

  return (
    <View style={styles.container}>
      <Text>Open up App.tsx to start working on your app!</Text>
      <StatusBar style="auto" />

      <Button title="Zelle Transfer" onPress={buttonClicked} />
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
