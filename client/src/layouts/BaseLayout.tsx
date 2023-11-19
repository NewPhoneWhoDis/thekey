import React from "react";

interface LayoutProps {
  children: React.ReactNode;
  className?: string;
}

const BaseLayout = ({ children, className }: LayoutProps) => {
  return (
    <main
      className={`py-16 grid gap-8 ${className?.length ? " " + className : ""}
    grid-cols-auto-fill-minmax-320`}
    >
      {children}
    </main>
  );
};

export default BaseLayout;
