import { StatusBar } from "expo-status-bar";
import { useEffect } from "react";
import { Button, StyleSheet, Text, View } from "react-native";
import { NativeModules } from "react-native";

export default function App() {
  const openZelle = NativeModules.OpenZelleModule;
  const OpenZelle = async () => {
    try {
      openZelle.open();
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {}, []);

  return (
    <View style={styles.container}>
      <Text>Open up App.tsx to start working on your app!</Text>
      <StatusBar style="auto" />

      <Button title="Zelle Transfer" onPress={OpenZelle} />
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
